package com.sagar.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaConsumerSubscribeApp {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("group.id", "test");
		
		KafkaConsumer myConsumer = new KafkaConsumer(props);
		
		ArrayList<String> topics = new ArrayList<>();		
		
		topics.add("my_topic");
		topics.add("my_other_topic");
		
////		if it is one topic to subscribe
//		myConsumer.subscribe(Arrays.asList("my_topic"));		
		
//		multiple topics
		myConsumer.subscribe(topics);
		
		try {
			while (true) {
				ConsumerRecords<String, String> records = myConsumer.poll(10);
				for (ConsumerRecord<String, String> record : records) {
					// process each record
					System.out.println(
						String.format("Tpoc: %s, partition: %d, offset: %d, key: %s, value: %s", 
								record.topic(), record.partition(), record.offset(), record.key(), record.value())
						);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			myConsumer.close();
		}
		
		
	}

}
