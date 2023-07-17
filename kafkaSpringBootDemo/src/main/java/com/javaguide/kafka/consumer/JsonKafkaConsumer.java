package com.javaguide.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.javaguide.kafka.payload.User;

@Service
public class JsonKafkaConsumer {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(JsonKafkaConsumer.class);
	
	@KafkaListener(topics = "javaguide_json", groupId = "json-group")
	public void consume(User user) {
		LOGGER.info(String.format("Json Message Received -> %s", user.toString()));
	}

}
