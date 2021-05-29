package vlu.architect.team7.futa.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FutaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FutaServerApplication.class, args);
    }
}
