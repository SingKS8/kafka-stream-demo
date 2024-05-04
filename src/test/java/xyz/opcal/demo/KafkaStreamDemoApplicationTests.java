package xyz.opcal.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaStreamDemoApplicationTests {

	@Test
	void waitConsume() throws InterruptedException {
		// waiting message consume
		Thread.sleep(60000);
	}

}
