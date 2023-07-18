package com.javaguide.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.javaguide.springboot.entity.wikimediaData;
import com.javaguide.springboot.repository.WikimediaDataRepository;

@Service
public class WikiMediaChangesConsumer {

	private static final Logger LOGGER= LoggerFactory.getLogger(WikiMediaChangesConsumer.class);
	
	private WikimediaDataRepository dataRepository;	
		
	public WikiMediaChangesConsumer(WikimediaDataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	@KafkaListener(topics = "wikimedia_topic", groupId = "wikimedia_group")
	public void consume(String messageEvent) {
		LOGGER.info(String.format("event received -> %s", messageEvent));
		
		wikimediaData wikimediaData = new wikimediaData();
		wikimediaData.setWikiEventData(messageEvent);
		
		dataRepository.save(wikimediaData); 
		
	}
	
	
//	To insert data into wikimedia table using jpa without using kafka consumer
//	add this insertInDB method in SpringBootConsumerApplication as commandline runner so that this method will execute
	
//	public void insertInDB() {
//		wikimediaData wikimediaData = new wikimediaData();
//		wikimediaData.setWikiEventData("Sagar");
//		LOGGER.info(String.format("Mysql DB record before insert-> %s", wikimediaData));
//		dataRepository.save(wikimediaData);
//		LOGGER.info("After dataRepository.save");
//	}
	
}
