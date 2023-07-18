package com.javaguide.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.javaguide.kafka.payload.User;

@Service
public class JsonKafkaProducer {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(JsonKafkaProducer.class);
	
	KafkaTemplate<String, User> kafkaTemplate;

	public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(User data) {
		
		LOGGER.info(String.format("Json Message being sent to kafka topic : %s", data.toString()));
		
		Message<User> message= MessageBuilder
							.withPayload(data)
							.setHeader(KafkaHeaders.TOPIC, "javaguide_json")
							.build();
		
		kafkaTemplate.send(message);
	}

}
