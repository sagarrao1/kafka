package com.javaguide.kafka.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaguide.kafka.producer.kafkaProducer;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

	private kafkaProducer kafkaProducer;

	public MessageController(com.javaguide.kafka.producer.kafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}
	
//	http:localhost:8080/api/v1/kafka/publish?message=Hello world
	@GetMapping("/publish")
	public ResponseEntity<String> publish(@RequestParam("message") String message){
		
		kafkaProducer.sendMessage(message);
		return ResponseEntity.ok("Message sent to topic");
	}
}
