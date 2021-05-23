package vlu.architect.team7.futa.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class FutaGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(FutaGatewayApplication.class, args);
    }
}
