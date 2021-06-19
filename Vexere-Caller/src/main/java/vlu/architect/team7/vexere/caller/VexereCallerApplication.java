package vlu.architect.team7.vexere.caller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class VexereCallerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VexereCallerApplication.class, args);
    }
}