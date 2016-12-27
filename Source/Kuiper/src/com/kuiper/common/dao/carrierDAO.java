package com.kuiper.common.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kuiper.common.constants.Constant;
import com.kuiper.common.model.Carrier;
import com.kuiper.common.model.StoreOrder;


public class carrierDAO {
	final static Logger logger = Logger.getLogger(carrierDAO.class);

	public static String saveCarrier(Carrier carrier) {
		try {
			logger.info("save Carrier");
			DBInit.mongoTemplate.save(carrier);
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;

		}
		return Constant.STR_SUCCESS;

	}

	public static String isDeliveryExist(String carrierId) {
		logger.info("run query");
		long recCount = 0;
		try {
			recCount = DBInit.mongoTemplate.count(new Query(Criteria.where("_id").is(carrierId)),
					Carrier.class);
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
	
	public static List<Carrier> getCarrierList() {

		DBInit.initateDB();
		try {
			logger.info("run query");
			Criteria criteria = new Criteria();
			Query query = Query.query(criteria);
			return DBInit.mongoTemplate.find(query, Carrier.class);
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return null;

		} 
	}

	public static String deleteCarrier(Carrier carrier) {
		try {
			logger.info("Delete Carrier");
			DBInit.mongoTemplate.remove(carrier);
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;

		}
		return Constant.STR_SUCCESS;

	}

	public static boolean isExistStoreOrder(String carrier_id) {
		logger.info("run query");
		long recCount = 0;
		try {
			recCount = DBInit.mongoTemplate.count(new Query(Criteria.where("carrier._id").is(carrier_id)),
					StoreOrder.class);
			if (recCount != 0) {
				return true;
			}
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return true;

		}
		return false;
	}


}
