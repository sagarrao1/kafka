package com.dailycode.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.dailycode.model.Book;

@Component
public class KafkaConsumer {

	@KafkaListener(topics="first_topic", groupId = "g1")
	public void consumer(String msg) {
		
		System.out.println("message : "+msg);
	}
	
//	@KafkaListener(topics="first_topic", groupId = "g1", containerFactory = "bookKafkaListenerContainerFactory")
//	public void consumer(Book book) {
//		
//		System.out.println(book);
//	}
	
}
