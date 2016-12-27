package com.kuiper.shipping.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kuiper.shipping.constants.Constant;
import com.kuiper.shipping.model.Carrier;
import com.kuiper.shipping.model.StoreOrder;

public class LoadBuilderDAO {
	
	final static Logger logger = Logger.getLogger(LoadBuilderDAO.class);
	
	public List<StoreOrder> loadStrOrdList(int storeOrd_num) {
		try {
			logger.info("Load POList");

			Query query = Query.query(Criteria.where("_id").is(storeOrd_num));
			return DBInit.mongoTemplate.find(query, StoreOrder.class);

		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return null;

		}
	}

	
	public String saveStoreOrder(StoreOrder storeOrder) {

		try {
			logger.info("save store Order");
			DBInit.mongoTemplate.save(storeOrder);
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.DB_SAVE_FAILED;

		}
		return Constant.STR_SUCCESS;
	}

	public String isDeliveryExist(int delivery_num) {
		logger.info("run query");
		long recCount = 0;
		try {
			recCount = DBInit.mongoTemplate.count(new Query(Criteria.where("delivery.delivery_num").is(delivery_num)),
					StoreOrder.class);
			if (recCount != 0) {
				return Constant.DELIVERY_EXIST;
			}
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;

		}
		return Constant.STR_SUCCESS;
	}


	public Carrier getCarrierid(String carrier_id) {
		try {
			logger.info("Load POList");

			Query query = Query.query(Criteria.where("_id").is(carrier_id));
			return DBInit.mongoTemplate.findOne(query, Carrier.class);

		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return null;

		}
	}
}
