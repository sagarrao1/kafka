package com.javaguide.kafka.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaguide.kafka.payload.User;
import com.javaguide.kafka.producer.JsonKafkaProducer;
import com.javaguide.kafka.producer.MyKafkaProducer;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

	private JsonKafkaProducer jsonkafkaProducer;

	public JsonMessageController(JsonKafkaProducer jsonkafkaProducer) {
		this.jsonkafkaProducer = jsonkafkaProducer;
	}
	
	
	@PostMapping("/publishJson")
	public ResponseEntity<String> publishJsonMsg(@RequestBody  User user) {
		jsonkafkaProducer.sendMessage(user);		
		return ResponseEntity.ok("Json Message sent to kafka topic javaguide_json successfully");		
	}
	
	
	
}
