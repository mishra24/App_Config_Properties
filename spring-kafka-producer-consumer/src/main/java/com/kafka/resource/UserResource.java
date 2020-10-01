package com.kafka.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class UserResource {
	
	@Autowired
	KafkaTemplate<String, User> kafkaTemplate;
	
	private final String TOPIC = "kfone";
	@GetMapping("/publish/{message}")
	public String post(@PathVariable("message") String message) {
		System.out.println(message+"................");
		User user = new User(message, 20);
		
		
		 kafkaTemplate.send(TOPIC,user); 
		 /* 
		 * future.addCallback(null, new
		 * ListenableFutureCallback<SendResult<String, String>>() {
		 * 
		 * @Override public void onSuccess(SendResult<String, String> result) {
		 * System.out.println("Sent message=[" + message + "] with offset=[" +
		 * result.getRecordMetadata().offset() + "]"); }
		 * 
		 * @Override public void onFailure(Throwable ex) {
		 * System.out.println("Unable to send message=[" + message + "] due to : " +
		 * ex.getMessage()); } });
		 */
		return "publish successfully";
	}
}
