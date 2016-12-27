//package com.kuiper.report.dao;
//
//import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
//import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
//import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
//
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.springframework.data.mongodb.core.aggregation.Aggregation;
//import org.springframework.data.mongodb.core.aggregation.AggregationResults;
//
//import com.kuiper.report.model.BalanceOnHand;
//import com.kuiper.report.model.CustomAggregationOperation;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
//
//
//public class BohDAO {
//
//	final static Logger logger = Logger.getLogger(BohDAO.class);
//
//	
//
//	public static List<BalanceOnHand> getBohList() {
//
//		DBInit.initateDB();
//		try {
//			logger.info("run query");
//			
//			
//			Aggregation aggregation = newAggregation(
//				    new CustomAggregationOperation(
//				        new BasicDBObject(
//				            "$group",
//				            new BasicDBObject("_id",
//				                new BasicDBObject("item_nbr", new BasicDBObject("item_nbr","$item_nbr"))
//				                .append("month", new BasicDBObject("$month", "$date"))
//				                .append("year", new BasicDBObject("$year", "$date"))
//				            )
//				            .append("totalPrice", new BasicDBObject(
//				                "$sum", new BasicDBObject(
//				                    "$multiply", Arrays.asList("$price","$quantity")
//				                )
//				            ))
//				            .append("averageQuantity", new BasicDBObject("$avg", "$quantity"))
//				            .append("count",new BasicDBObject("$sum",1))
//				        )
//				    )
//				)
//			
//
//			Aggregation agg = newAggregation(group("po_status").sum().as("total"),
//					project("total").and("po_status").previousOperation());
//			
//			
//			AggregationResults<PoSummaryCnt> groupresult = mongoTemplate.aggregate(agg,"PurchaseOrder",
//					PoSummaryCnt.class);
//
//			List<PoSummaryCnt> result = groupresult.getMappedResults();
//			
//			
//			DBObject groupFields = new BasicDBObject()
//					groupFields.put('item_nbr', "\$item_nbr")
//					groupFields.put('status', "\$action")
//					def result = collection.aggregate(Arrays.asList(Aggregates.group(groupFields, []))).iterator()
//					
//					
//			Criteria criteria = new Criteria();
//			Query query = Query.query(criteria);
//			return DBInit.mongoTemplate.find(query, StoreOrder.class);
//		} catch (Exception e) {
//			ByteArrayOutputStream os = new ByteArrayOutputStream();
//			e.printStackTrace(new PrintStream(os));
//			logger.error(new String(os.toByteArray()));
//			return null;
//
//		} finally {
//
//		}
//	}
//}
