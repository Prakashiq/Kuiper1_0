package com.kuiper.report.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kuiper.report.constants.Constant;
import com.kuiper.report.model.Item;

public class ItemDownloadDAO {
	final static Logger logger = Logger.getLogger(ItemDownloadDAO.class);

	public static List<Item> getItemList() {
		DBInit.initateDB();
		try {
			logger.info("run query");
			Criteria criteria = new Criteria().andOperator(Criteria.where("item_status_code").in(Constant.ACTIVE_ITEM));
			Query query = Query.query(criteria);
			return DBInit.mongoTemplate.find(query, Item.class);
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return null;

		} finally {

		}
	}

}
