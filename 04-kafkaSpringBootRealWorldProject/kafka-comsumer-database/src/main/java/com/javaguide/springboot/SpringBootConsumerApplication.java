package com.javaguide.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootConsumerApplication /* implements CommandLineRunner */{
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootConsumerApplication.class);
	}

//	@Autowired
//	private WikiMediaChangesConsumer  wikiMediaChangesConsumer;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		wikiMediaChangesConsumer.insertInDB();
//	}

}
