package com.kuiper.receive.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;

import com.kuiper.receive.constants.Constant;
import com.kuiper.receive.model.PurchaseOrder;

public class UpdatePODao {
	final static Logger logger = Logger.getLogger(GetPODetailsDAO.class);

	public static String update(PurchaseOrder po) {
		logger.info("read xml");

		try {
			if (DBInit.initateDB() == Constant.STR_SUCCESS) {
				logger.info("run query: delivery  number " + po.get_id() + " po Status " + po.getPo_status());
				DBInit.mongoTemplate.save(po);
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
