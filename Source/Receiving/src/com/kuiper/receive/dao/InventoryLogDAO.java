package com.kuiper.receive.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;

import com.kuiper.receive.constants.Constant;
import com.kuiper.receive.dao.DBInit;
import com.kuiper.receive.model.InventoryLog;

public class InventoryLogDAO {

	final static Logger logger = Logger.getLogger(InventoryLogDAO.class);

	public static String saveDelivery(InventoryLog invtlog) {
		logger.info("read xml");

		try {
			if (DBInit.initateDB() == Constant.STR_SUCCESS) {
				DBInit.mongoTemplate.save(invtlog);
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