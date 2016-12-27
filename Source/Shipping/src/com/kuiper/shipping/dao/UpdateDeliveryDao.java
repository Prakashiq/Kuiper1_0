package com.kuiper.shipping.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kuiper.shipping.constants.Constant;
import com.kuiper.shipping.model.StoreOrder;
import com.kuiper.shipping.model.UpdateTrailer;

public class UpdateDeliveryDao {

	final static Logger logger = Logger.getLogger(GetStrOrdDetailsDAO.class);

	public static String update(UpdateTrailer updatetrailer) {

		try {
			if (DBInit.initateDB() == Constant.STR_SUCCESS) {
				logger.info("run query: Order Number " + updatetrailer.getOrder_num() + " Order Status "
						+ updatetrailer.getOrder_status());
				Query query = Query.query(Criteria.where("_id").is(updatetrailer.getOrder_num()));
				StoreOrder storeorder = DBInit.mongoTemplate.findOne(query, StoreOrder.class);
				if (storeorder != null) {
					storeorder.setOrder_status(updatetrailer.getOrder_status());
					if (updatetrailer.getOrder_status().equalsIgnoreCase(Constant.INIT_CO)) {
						storeorder.setTrailer(null);
						storeorder.setCarrier(null);
					}
					DBInit.mongoTemplate.save(storeorder);
					return Constant.STR_SUCCESS;
				} else {
					return Constant.LOAD_NOT_FOUND;
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
