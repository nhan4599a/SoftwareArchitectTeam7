package vlu.architect.team7.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class SearchingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchingServiceApplication.class, args);
	}

}