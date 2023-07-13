package com.pluralsight.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.pluralsight.kafka.producer.model.Event;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	public static void main(String[] args) {
		
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		KafkaProducer<String,String> myProducer = new KafkaProducer<>(props);
		ProducerRecord<String,String> myRecord = null;		
		
		EventGenerator eventGenerator = new EventGenerator();
				
		try {			
			for (int i = 0; i < 15 ; i++) {							
				log.info("Generating event #" + i);
				Event event = eventGenerator.generateEvent();				
				
				String key = extractKey(event);
				String value = extractValue(event);				
				
				myRecord=  new ProducerRecord<>("first_topic", key, value);		
				log.info("Producing to Kafka the record: " + key + ":" + value);
				
				myProducer.send(myRecord);	
//				System.out.println("Producing to Kafka the record: " + key + ":" + value);
				
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
	
	private	static String extractKey(Event event) {
		return event.getUser().getUserId().toString();
		
	}

	private	static String extractValue(Event event) {
		return String.format("%s,%s,%s", event.getProduct().getType(), event.getProduct().getColor(), event.getProduct().getDesignType());
		
	}
}
