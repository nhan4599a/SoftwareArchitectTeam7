package vlu.architect.team7.def_garage.def_garage_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DefGarageServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefGarageServerApplication.class, args);
	}

}
