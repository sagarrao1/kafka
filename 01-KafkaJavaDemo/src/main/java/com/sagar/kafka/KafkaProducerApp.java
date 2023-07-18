package com.sagar.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerApp {

	public static void main(String[] args) {
		
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		KafkaProducer<String,String> myProducer = new KafkaProducer<>(props);
		ProducerRecord<String,String> myRecord = null;		
				
		try {			
			for (int i = 0; i < 150 ; i++) {			
				myRecord=  new ProducerRecord<>("first_topic", Integer.toString(i), "My Message: "+Integer.toString(i));			
				myProducer.send(myRecord);	
				System.out.println("My Message: "+Integer.toString(i));
				
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			myProducer.close();
		}
		
	}

}
