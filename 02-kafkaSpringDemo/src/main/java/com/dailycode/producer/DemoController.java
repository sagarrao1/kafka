package com.dailycode.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dailycode.model.Book;

@RestController
public class DemoController {

	@Autowired
	KafkaTemplate<String, Book> kafkaTemplate; 
	
	
	private static final String TOPIC="first_topic";
	
//	publishing String message to kafka topic
	
//	@GetMapping("/publish/{message}" )
//	public String publishMessage(@PathVariable String message) {
//		
//		kafkaTemplate.send(TOPIC, message);
//		return "Message published successfully";
//	}
	

	
//	publishing json message to kafka topic
	@PostMapping("/publish" )
	public String publishMessage(@RequestBody Book book) {
		
		kafkaTemplate.send(TOPIC, book);
		return "Message published successfully";
	}

	
}
