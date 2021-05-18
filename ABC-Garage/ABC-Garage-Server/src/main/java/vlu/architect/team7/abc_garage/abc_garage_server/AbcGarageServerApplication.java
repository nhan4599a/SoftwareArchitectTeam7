package vlu.architect.team7.abc_garage.abc_garage_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AbcGarageServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbcGarageServerApplication.class, args);
	}

}