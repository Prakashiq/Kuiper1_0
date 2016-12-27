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
import com.kuiper.common.model.StoreOrder;
import com.kuiper.common.model.PurchaseOrder;

public class StoreOrderDao {
	final static Logger logger = Logger.getLogger(StoreOrderDao.class);
	
	public static List<StoreOrder> getCOList() {
		try {
				DBInit.initateDB();
			
				logger.info("run query");
				   Query query = Query.query(Criteria.where("order_status").in(Constant.INIT_PO));    
				    query.fields().include("_id");
				    
				    return DBInit.mongoTemplate.find(query, StoreOrder.class); 
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return null;

		} 
	}
}
