package com.pluralsight.kafka.consumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.pluralsight.kafka.avro.model.Product;
import com.pluralsight.kafka.avro.model.User;

import java.time.Duration;
import java.util.Properties;

import static java.util.Arrays.asList;

@Slf4j
public class MainConsumerUsingAvro {

    public static void main(String[] args) {

        SuggestionEngine suggestionEngine = new SuggestionEngine();

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("group.id", "user-tracking-consumer");
        props.put("key.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        props.put("specific.avro.reader", "true");
        props.put("schema.registry.url", "http://localhost:8081");
        
        KafkaConsumer<User, Product> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(asList("first_topic"));

        while (true) {
            ConsumerRecords<User, Product> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<User, Product> record : records) {
            	log.info("Consuming Kafka record: " + record.key() + ":" + record.value());
            	System.out.println("Consuming Kafka record: " + record.key() + ":" + record.value());
            	
//                suggestionEngine.processSuggestions(record.key(), record.value());
            }
        }
    }
}
