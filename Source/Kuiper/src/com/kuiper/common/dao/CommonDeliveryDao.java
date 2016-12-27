package com.kuiper.common.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kuiper.common.constants.Constant;
import com.kuiper.common.model.PurchaseOrder;

public class CommonDeliveryDao {
	final static Logger logger = Logger.getLogger(CommonDeliveryDao.class);

	private static MongoTemplate mongoTemplate = null;
	private static ApplicationContext ctx = null;
	
	public static List<PurchaseOrder> getPOList() {

		
		logger.info("read xml");
		if (mongoTemplate == null) {
			ctx = new GenericXmlApplicationContext("Config.xml");
			mongoTemplate = (MongoTemplate) ctx.getBean("mongoTemplate");
		}

		try {
				logger.info("run query");
				   Query query = Query.query(Criteria.where("po_status").is(Constant.INIT_PO));    
				    query.fields().include("_id");
				    
				    return mongoTemplate.find(query, PurchaseOrder.class); 
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return null;

		} finally {

			mongoTemplate.getDb().getMongo().close();
			mongoTemplate = null;
		}
	}
}
