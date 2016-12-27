package com.kuiper.loadPO.bo;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;


public class BroadCastMessage {

	final static Logger logger = Logger.getLogger(BroadCastMessage.class);


	public  void sendmessage(String message) {
		Properties props = setProperties();

		Producer<String, String> producer = new KafkaProducer<String, String>(props);

		try {
			producer.send(new ProducerRecord<String, String>("repl_topic", message)).get();
			System.out.println("Processed ");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			producer.close();
		}
	}

	public  Properties setProperties() {
		Properties props = new Properties();

		// Assign localhost id
		props.put("bootstrap.servers", "localhost:9092");

		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		return props;
	}

}
