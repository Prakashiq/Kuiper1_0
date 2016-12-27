package com.kuiper.StoreOrd.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kuiper.StoreOrd.constants.Constant;
import com.kuiper.StoreOrd.model.Store;


public class StoreAlignmentDAO {
	final static Logger logger = Logger.getLogger(StoreAlignmentDAO.class);

	public static String loadStore(Store store) {
		try {
			DBInit.initateDB();
			logger.info("save Store Order");
			DBInit.mongoTemplate.save(store);
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;

		}
		return Constant.STR_SUCCESS;
	}

	public static String validateStoreNbr(Store store) {
		try {
			DBInit.initateDB();
			if (store != null) {
				logger.info("run query");
				if (DBInit.mongoTemplate.count(new Query(Criteria.where("storeName").is(store.get_id())), Store.class) == 0) {
			//		return Constant.ITEM_NOT_FOUND;
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
