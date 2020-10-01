package com.kafka.resource;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
	
	@KafkaListener(topics = "kfone",groupId = "group_id")
	public void consume(User user) {
		
		System.out.println("consumer retrieve msg" + user);
	}

}
