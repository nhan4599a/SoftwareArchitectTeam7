package vlu.architect.team7.def_garage.def_garage_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class DefGarageGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefGarageGatewayApplication.class, args);
	}

}
