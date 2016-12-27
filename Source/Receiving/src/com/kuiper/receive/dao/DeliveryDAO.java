package com.kuiper.receive.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kuiper.receive.constants.Constant;
import com.kuiper.receive.model.PurchaseOrder;

public class DeliveryDAO {
	
	final static Logger logger = Logger.getLogger(DeliveryDAO.class);
	
	public List<PurchaseOrder> loadPOList(int po_num) {
		try {
			logger.info("Load POList");

			Query query = Query.query(Criteria.where("_id").is(po_num));
			return DBInit.mongoTemplate.find(query, PurchaseOrder.class);

		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return null;

		}
	}

	
	public String saveDelivery(PurchaseOrder po) {

		try {
			logger.info("save PO");
			DBInit.mongoTemplate.save(po);
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.DB_SAVE_FAILED;

		}
		return Constant.STR_SUCCESS;
	}

	public String isDeliveryExist(int delivery_num) {
		logger.info("run query");
		long recCount = 0;
		try {
			recCount = DBInit.mongoTemplate.count(new Query(Criteria.where("delivery.delivery_num").is(delivery_num)),
					PurchaseOrder.class);
			if (recCount != 0) {
				return Constant.DELIVERY_EXIST;
			}
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;

		}
		return Constant.STR_SUCCESS;
	}
}
