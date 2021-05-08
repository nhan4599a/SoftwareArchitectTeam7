package vlu.architect.team7.ABCGarage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AbcGarageApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbcGarageApplication.class, args);
	}

}
