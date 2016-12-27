package com.kuiper.loadItem.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.kuiper.loadItem.constants.Constant;
import com.kuiper.loadItem.model.Item;

public class ItemDownloadDAO {
	final static Logger logger = Logger.getLogger(ItemDownloadDAO.class);


	
	public int loadItem(Item item) {
		
		try {
			logger.info("save Item");
			DBInit.initateDB();
			DBInit.mongoTemplate.save(item);
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.FAILURE;

		}
		return Constant.SUCCESS;
	}

}
