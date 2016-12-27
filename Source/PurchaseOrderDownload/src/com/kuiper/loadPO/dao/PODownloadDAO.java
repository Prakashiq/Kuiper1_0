package com.kuiper.loadPO.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kuiper.loadPO.constants.Constant;
import com.kuiper.loadPO.model.Item;
import com.kuiper.loadPO.model.PurchaseOrder;
import com.kuiper.loadPO.model.PurchaseOrderLine;
import com.kuiper.loadPO.model.Store;

public class PODownloadDAO {
	final static Logger logger = Logger.getLogger(PODownloadDAO.class);

	public String loadPO(PurchaseOrder po) {
		try {
			logger.info("save PO");
			DBInit.mongoTemplate.save(po);
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;
		}
		return Constant.STR_SUCCESS;
	}

	public static String validateItemNbr(@QueryParam("PurchaseOrderLine") PurchaseOrderLine poline) {

		try {

			if (poline != null) {
				logger.info("run query");
				long cnt = DBInit.mongoTemplate.count(new Query(
						Criteria.where("_id").is(poline.getItem_nbr()).and("item_status_code").is(Constant.ACTIVE_ITEM)),
						Item.class);
				if (cnt == 0) {
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

	public static String validateStoreNbr(String store_nbr) {
		try {
			
			if (store_nbr != null) {
				int strnbr= Integer.parseInt(store_nbr);
				logger.info("run query");
				long recCnt = DBInit.mongoTemplate.count(new Query(Criteria.where("_id").in(strnbr)),
						Store.class);
				if (recCnt == 0) {
					return Constant.STORENOTALIGNED;
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
