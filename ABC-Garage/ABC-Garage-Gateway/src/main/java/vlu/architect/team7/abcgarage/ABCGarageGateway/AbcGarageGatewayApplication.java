package vlu.architect.team7.abcgarage.ABCGarageGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class AbcGarageGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbcGarageGatewayApplication.class, args);
	}

}
