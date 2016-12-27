package com.kuiper.common.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;

import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kuiper.common.constants.Constant;
import com.kuiper.common.model.User;

public class LoginDao implements Serializable {

	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(LoginDao.class);

	public static int validate(@QueryParam("user") User user) {
		try {
			DBInit.initateDB();

			if (user != null) {
				logger.info("run query");
				if (DBInit.mongoTemplate.findOne(
						new Query(
								Criteria.where("userid").is(user.getUsername()).and("password").is(user.getPassword())),
						User.class) != null) {
					return Constant.SUCCESS;
				}

			}
		} catch (Exception e) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(os));
			logger.error(new String(os.toByteArray()));
			return Constant.FAILURE;

		}

		return Constant.FAILURE;
	}

}
