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
import com.kuiper.common.model.SoSummaryCnt;

public class SOSumCntDAO {

	final static Logger logger = Logger.getLogger(SOSumCntDAO.class);

	public static List<SoSummaryCnt> getSOSummaryCnt() {
		try {
			
			DBInit.initateDB();
			
			Aggregation agg = newAggregation(group("order_status").count().as("total"),
					project("total").and("order_status").previousOperation());
			AggregationResults<SoSummaryCnt> groupresult = DBInit.mongoTemplate.aggregate(agg,"StoreOrder",
					SoSummaryCnt.class);

			List<SoSummaryCnt> result = groupresult.getMappedResults();
			

			return result;
			
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return null;
		}
	}
}
