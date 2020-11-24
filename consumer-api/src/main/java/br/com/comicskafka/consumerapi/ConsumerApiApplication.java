package br.com.comicskafka.consumerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ConsumerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApiApplication.class, args);
	}

}
