package com.pluralsight.kafka.producer;

import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.pluralsight.kafka.avro.model.*;
import com.pluralsight.kafka.producer.model.Event;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainProducerUsingAvro {

	public static void main(String[] args) {
		
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
		props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
//		props.put("schema.registry.url", "http://172.25.10.132:8081");
//		props.put("schema.registry.url", "http://localhost:8081");
		
		KafkaProducer<User,Product> myProducer = new KafkaProducer<>(props);
		ProducerRecord<User,Product> myRecord = null;		
		
		EventGenerator eventGenerator = new EventGenerator();
				
		try {			
			for (int i = 0; i < 15 ; i++) {							
				log.info("Generating event #" + i);
				Event event = eventGenerator.generateEvent();				
				
				User key = extractKey(event);
				Product value = extractValue(event);				
				
				myRecord=  new ProducerRecord<>("first_topic", key, value);		
				log.info("Producing to Kafka the record: " + key + ":" + value);
				
				myProducer.send(myRecord);	
				System.out.println("Producing to Kafka the record: " + key + ":" + value);
				
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
	
	private	static User extractKey(Event event) {
//		return event.getUser().getUserId().toString();
		return User.newBuilder()
				.setUserId(event.getInternalUser().getUserId().toString())
				.setUsername(event.getInternalUser().getUsername())
				.setDateOfBirth((int)event.getInternalUser().getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).getLong(ChronoField.EPOCH_DAY) )
				.build();
	}

	private	static Product extractValue(Event event) {
//		return String.format("%s,%s,%s", event.getProduct().getType(), event.getProduct().getColor(), event.getProduct().getDesignType());
		
		 return Product.newBuilder()
	                .setProductType(ProductType.valueOf(event.getInternalProduct().getType().name()))
	                .setColor(Color.valueOf(event.getInternalProduct().getColor().name()))
	                .setDesignType(DesignType.valueOf(event.getInternalProduct().getDesignType().name()))
	                .build();
		
	}
}
