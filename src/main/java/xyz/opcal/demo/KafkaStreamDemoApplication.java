package xyz.opcal.demo;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class KafkaStreamDemoApplication implements CommandLineRunner {

	Logger producerLogger = LoggerFactory.getLogger("producer");

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamDemoApplication.class, args);
	}

	@Bean
	NewTopic newUser() {
		return TopicBuilder.name("new_user").partitions(3).build();
	}

	@Bean
	NewTopic topicAgeCount() {
		return TopicBuilder.name("user_age_count").partitions(3).build();
	}

	@Autowired
	StreamBridge streamBridge;

	@Override
	public void run(String... args) throws Exception {
		Random random = new Random();

		for (int i = 0; i < 50; i++) {
			User data = new User(RandomStringUtils.randomAlphabetic(10), random.nextInt(0, 100));
			streamBridge.send("new-user-out-0", data);
			producerLogger.info("#### send user [{}]", data);
		}
	}

}

record User(String name, Integer age) {

}
