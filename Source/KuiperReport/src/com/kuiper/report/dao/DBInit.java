package com.kuiper.report.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.kuiper.report.constants.Constant;

public class DBInit {

	final static Logger logger = Logger.getLogger(DBInit.class);

	public static MongoTemplate mongoTemplate = null;
	public static ApplicationContext ctx = null;

	public static String initateDB() {
		try {
			logger.info("read xml");

			if (mongoTemplate == null) {
				ctx = new GenericXmlApplicationContext("Config.xml");
				mongoTemplate = (MongoTemplate) ctx.getBean("mongoTemplate");
			}

		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.DB_INIT_FAILED;

		}
		return Constant.STR_SUCCESS;
	}

	
	public static void closeDB() {
		mongoTemplate.getDb().getMongo().close();
		mongoTemplate = null;
	}

}
