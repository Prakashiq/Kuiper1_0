package com.kuiper.StoreOrd.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kuiper.StoreOrd.constants.Constant;
import com.kuiper.StoreOrd.model.InvCnt;
import com.kuiper.StoreOrd.model.InventoryLog;
import com.kuiper.StoreOrd.model.Item;
import com.kuiper.StoreOrd.model.Store;
import com.kuiper.StoreOrd.model.StoreOrder;
import com.kuiper.StoreOrd.model.StoreOrderLine;
import com.kuiper.StoreOrd.model.StoreOrder_ex;



public class StoreOrdDownloadDAO {
	final static Logger logger = Logger.getLogger(StoreOrdDownloadDAO.class);

	public String loadstoreOrder(StoreOrder storeOrder) {
		try {
			DBInit.initateDB();
			logger.info("save storeOrder");
			DBInit.mongoTemplate.save(storeOrder);
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;

		}
		return Constant.STR_SUCCESS;
	}

	public String loadstoreOrder_ex(StoreOrder_ex storeOrderex) {
		try {
			DBInit.initateDB();
			logger.info("save storeOrder");
			DBInit.mongoTemplate.save(storeOrderex);
			
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;

		}
		return Constant.STR_SUCCESS;
	}
	
	
	public static String validateItemNbr(@QueryParam("storeOrderLine") StoreOrderLine orderline) {

		try {

			if (orderline != null) {
				logger.info("run query");
				if (DBInit.mongoTemplate.count(new Query(Criteria.where("_id").in(orderline.getItem_nbr())
						.and("item_status_code").is(Constant.ACTIVE_ITEM)), Item.class) == 0) {
					return Constant.ITEM_NOT_FOUND;

				}
			}
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;
		}

		return Constant.STR_SUCCESS;
	}

	public static String validateqty(StoreOrderLine strOrdline) {

		long rcvQty = 0;
		long shpQty = 0;

		try {
			if (strOrdline != null) {
				logger.info("run query");

				Aggregation agg = Aggregation.newAggregation(match(Criteria.where("item_nbr").in(strOrdline.getItem_nbr())),
						group("action").sum("qty").as("total_qty"), project("total_qty"));
				AggregationResults<InvCnt> results = DBInit.mongoTemplate.aggregate(agg, InventoryLog.class, InvCnt.class);
				
				if (results.getMappedResults().isEmpty()) {
					return Constant.OUT_OF_STOCK;
				}
				List<InvCnt> invCntLst = results.getMappedResults();

				for(InvCnt invCnt: invCntLst) {

					if (invCnt.get_id().equalsIgnoreCase(Constant.RECEIVING)) {
						rcvQty = rcvQty + invCnt.getTotal_qty();
					}
					if (invCnt.get_id().equalsIgnoreCase(Constant.SHIIPPING)) {
						shpQty =  shpQty + invCnt.getTotal_qty();
					}

					if (invCnt.get_id().equalsIgnoreCase(Constant.ORDER_ALLOCATED)) {
						shpQty = shpQty + invCnt.getTotal_qty();
					}	
					
				}
				
				if ((rcvQty - shpQty) < strOrdline.getOrdered_qty()) {
					return Constant.OUT_OF_STOCK;
				}

				return Constant.STR_SUCCESS;

			}
		} catch (

		Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;
		}

		return Constant.STR_SUCCESS;
	}

	public static String validateOrdNumber(int ordnumber) {
		try {

			if (ordnumber != 0) {
				logger.info("run query");
				if (DBInit.mongoTemplate.count(new Query(Criteria.where("_id").in(ordnumber)), StoreOrder.class) == 0) {
					return Constant.STR_SUCCESS;

				}
			}
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;
		}

		return  Constant.STOREORDEREXIST;
	}

	public static String validateStoreNbr(StoreOrder storeOrd) {
		try {

			if (storeOrd != null) {
				logger.info("run query");
				if (DBInit.mongoTemplate.count(new Query(Criteria.where("_id").in(storeOrd.getStore_nbr())), Store.class) == 0) {
					return  Constant.STORENOTALIGNED;
				}
			}
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;
		}

		return Constant.STR_SUCCESS;
	}

}
