package vlu.architect.team7.server.DEFGarage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DefGarageApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefGarageApplication.class, args);
	}

}
