package com.dailycode.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.dailycode.model.Book;

@EnableKafka
@Configuration
public class KafkaComsumerConfig {

	
//	@Bean
//	public ConsumerFactory<String, String> cosumerFactory(){
//		
//		Map<String,Object> config= new HashMap<>();
//		
//		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		config.put(ConsumerConfig.GROUP_ID_CONFIG, "g1");
//				
//		return new DefaultKafkaConsumerFactory<>(config);
//	}
//	
//	@Bean
//	public  ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//		
//		ConcurrentKafkaListenerContainerFactory<String, String> factory= new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(cosumerFactory());
//		return factory;		
//	}
	
	
	
	@Bean
	public ConsumerFactory<String, Book> bookCosumerFactory(){
		
		Map<String,Object> config= new HashMap<>();
		
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "g1");
				
		return new DefaultKafkaConsumerFactory<>(config,
					new StringDeserializer(),
					new JsonDeserializer<>(Book.class));
	}
	
	@Bean
	public  ConcurrentKafkaListenerContainerFactory<String, Book> bookKafkaListenerContainerFactory() {
		
		ConcurrentKafkaListenerContainerFactory<String, Book> factory1= new ConcurrentKafkaListenerContainerFactory<>();
		factory1.setConsumerFactory(bookCosumerFactory());
		return factory1;		
	}
	
	
	
	
	
	

//	@Bean
//	public  ConcurrentKafkaListenerContainerFactory<String, String> filterKafkaListenerContainerFactory() {
//		
//		ConcurrentKafkaListenerContainerFactory<String, String> factory1= new ConcurrentKafkaListenerContainerFactory<>();
//		factory1.setConsumerFactory(cosumerFactory());
//		factory1.setRecordFilterStrategy(
//				record -> record.value().contains("world")
//				);
//		return factory1;		
//	}

}
