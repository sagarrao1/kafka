package com.javaguide.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafkaTopicConfig {
	
	@Bean
	public NewTopic newTopic() {		
		return TopicBuilder.name("javaguide")
				.build();
	}
	
	@Bean
	public NewTopic newJsonTopic() {		
		return TopicBuilder.name("javaguide_json")
				.build();
	}

}
