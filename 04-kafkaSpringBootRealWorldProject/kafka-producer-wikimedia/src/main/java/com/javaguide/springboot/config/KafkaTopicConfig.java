package com.javaguide.springboot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	private static final Logger LOGGER= LoggerFactory.getLogger(KafkaTopicConfig.class);
	
	@Bean
	public NewTopic createTopic() {
		LOGGER.info(" creating wikimedia_topic topic");
		return TopicBuilder.name("wikimedia_topic")
				.build();
	}
	
}
