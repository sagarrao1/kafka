package com.javaguide.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WikiMediaChangesConsumer {

	private static final Logger LOGGER= LoggerFactory.getLogger(WikiMediaChangesConsumer.class);
	
	@KafkaListener(topics = "wikimedia_topic", groupId = "wikimedia_group")
	public void consume(String messageEvent) {
		LOGGER.info(String.format("event received -> %s", messageEvent));
	}
	
}
