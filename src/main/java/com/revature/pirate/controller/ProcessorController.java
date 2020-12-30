package com.revature.pirate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProcessorController {
	@Autowired
	private AmazonSQS sqsClient;
	
	private final String QUEUE_URL=System.getenv("QUEUE_URL");
	
	@PostMapping("/process/")
	public String processHire() {
		log.warn("Order is being processed....");
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest().withQueueUrl(QUEUE_URL);
		List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).getMessages();

		log.warn("Messages received from queue"+ messages);
		return messages.toString();
		
	}
}
