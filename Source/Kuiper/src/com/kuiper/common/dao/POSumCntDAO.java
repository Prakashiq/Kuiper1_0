package com.kuiper.common.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import com.kuiper.common.constants.Constant;
import com.kuiper.common.model.PoSummaryCnt;

public class POSumCntDAO {

	final static Logger logger = Logger.getLogger(POSumCntDAO.class);
	private static MongoTemplate mongoTemplate = null;
	private static ApplicationContext ctx = null;

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
			return Constant.STR_FAILURE;

		}
		return Constant.STR_SUCCESS;
	}



	public static List<PoSummaryCnt> getPOSummaryCnt() {
		try {
			logger.info("run query");

			Aggregation agg = newAggregation(group("po_status").count().as("total"),
					project("total").and("po_status").previousOperation());
			AggregationResults<PoSummaryCnt> groupresult = mongoTemplate.aggregate(agg,"PurchaseOrder",
					PoSummaryCnt.class);

			List<PoSummaryCnt> result = groupresult.getMappedResults();

			return result;
			
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return null;
		}
	}
}
