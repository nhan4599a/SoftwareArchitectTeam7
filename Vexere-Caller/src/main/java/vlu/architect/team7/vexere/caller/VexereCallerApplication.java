package vlu.architect.team7.vexere.caller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VexereCallerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VexereCallerApplication.class, args);
    }
}