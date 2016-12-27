package com.kuiper.report.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kuiper.report.model.StoreOrder;
import com.kuiper.report.model.PurchaseOrder;


public class StoreOrderDetailsDAO {

	final static Logger logger = Logger.getLogger(StoreOrderDetailsDAO.class);

	

	public static List<StoreOrder> getCOList() {

		DBInit.initateDB();
		try {
			logger.info("run query");
			Criteria criteria = new Criteria();
			Query query = Query.query(criteria);
			return DBInit.mongoTemplate.find(query, StoreOrder.class);
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return null;

		} finally {

		}
	}
}
