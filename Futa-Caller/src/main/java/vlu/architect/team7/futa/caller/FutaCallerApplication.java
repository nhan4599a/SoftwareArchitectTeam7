package vlu.architect.team7.futa.caller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class FutaCallerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FutaCallerApplication.class, args);
    }
}