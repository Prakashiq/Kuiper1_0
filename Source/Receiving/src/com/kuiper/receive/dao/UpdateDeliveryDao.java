package com.kuiper.receive.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kuiper.receive.constants.Constant;
import com.kuiper.receive.model.PurchaseOrder;
import com.kuiper.receive.model.UpdateDelivery;

public class UpdateDeliveryDao {

	final static Logger logger = Logger.getLogger(GetPODetailsDAO.class);

	public static String update(UpdateDelivery updatedelivery) {

		try {
			if (DBInit.initateDB() == Constant.STR_SUCCESS) {
				logger.info("run query: delivery  number " + updatedelivery.getDelivery_num() + " po Status "
						+ updatedelivery.getPo_status());
				Query query = Query.query(Criteria.where("delivery.delivery_num").is(updatedelivery.getDelivery_num()));
				PurchaseOrder purchaseorder = DBInit.mongoTemplate.findOne(query, PurchaseOrder.class);
				if (purchaseorder != null) {
					purchaseorder.setPo_status(updatedelivery.getPo_status());
					if (updatedelivery.getPo_status().equalsIgnoreCase(Constant.INIT_PO)) {
						purchaseorder.setDelivery(null);
					}
					DBInit.mongoTemplate.save(purchaseorder);
					return Constant.STR_SUCCESS;
				} else {
					return Constant.DELIVERY_NOT_FOUND;
				}
			} else {
				return Constant.DB_INIT_FAILED;
			}
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.EXECEPTION;

		} finally {
		}
	}

}
