package com.kuiper.loadItem.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import com.mongodb.DB;
import com.mongodb.MongoClient;

@Configuration
@PropertySources(@PropertySource("classpath:config.properties"))
public class MongoSingleton  {

	final static Logger logger = Logger.getLogger(MongoSingleton.class);

	private static MongoSingleton mDbSingleton;

	private static MongoClient mongoClient;

	private static DB db;

	private static String dbHost = "127.0.0.1";
	private static int dbPort = 27017;
	private static String dbName = "testdb";

	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

	public MongoSingleton() {

	};

	public static  MongoSingleton getInstance() {

		if (mDbSingleton == null) {
			mDbSingleton = new MongoSingleton();
		}
		return mDbSingleton;
	}

	/* (non-Javadoc)
	 * @see com.kuiper.loadItem.dao.IMongoSingleton#getMongodb()
	 */

	public DB getMongodb() {
		if (mongoClient == null) {

			try {

				ctx.register(MongoSingleton.class);
				ctx.refresh();
				Environment env = ctx.getEnvironment();
				dbHost = env.getProperty("mongodb.host");
				dbPort = Integer.parseInt(env.getProperty("mongodb.port"));
				dbName = env.getProperty("mongodb.dbname");

				logger.info("Connecting to:" + dbHost + ":" + dbPort);

				mongoClient = new MongoClient(dbHost, dbPort);
				db = mongoClient.getDB(dbName);

			} catch (Exception e) {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				e.printStackTrace(new PrintStream(os));
				logger.error(new String(os.toByteArray()));

			} finally {
				ctx.close();
			}

		}

		/*
		 * if (! db.isAuthenticated()) { boolean auth = db.authenticate(dbUser,
		 * dbPassword.toCharArray()); }
		 */
		return db;
	}

	
	/* (non-Javadoc)
	 * @see com.kuiper.loadItem.dao.IMongoSingleton#closeDb()
	 */
	public int closeDb() {
		if (mongoClient != null) {
			mongoClient.close();
			logger.info("disconnected to MongoDB");
		}
		return 0;

	}
}
