package vlu.architect.team7.vexere.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class VexereServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(VexereServerApplication.class, args);
    }
}
