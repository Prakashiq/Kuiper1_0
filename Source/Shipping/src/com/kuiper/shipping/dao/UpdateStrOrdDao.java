package com.kuiper.shipping.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;

import com.kuiper.shipping.constants.Constant;
import com.kuiper.shipping.model.StoreOrder;

public class UpdateStrOrdDao {
	final static Logger logger = Logger.getLogger(GetStrOrdDetailsDAO.class);

	public static String update(StoreOrder co) {
		logger.info("read xml");

		try {
			if (DBInit.initateDB() == Constant.STR_SUCCESS) {
				logger.info("run query: delivery  number " + co.get_id() + " po Status " + co.getOrder_status());
				DBInit.mongoTemplate.save(co);
				return Constant.STR_SUCCESS;
			}
			return Constant.DB_INIT_FAILED;
		} catch (

		Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;

		} finally {
		}
	}
}
