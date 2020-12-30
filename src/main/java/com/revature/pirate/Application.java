package com.revature.pirate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@EnableEurekaClient
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public AmazonSQS buildSQS() {
		 AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(
					new BasicAWSCredentials(System.getenv("ACCESS_KEY"), System.getenv("SECRET_ACCESS_KEY")));
		 AmazonSQS sqsClient = AmazonSQSClientBuilder.standard().withCredentials(credentialsProvider).withRegion(Regions.US_EAST_2).build();
		 return sqsClient;
	}

}
