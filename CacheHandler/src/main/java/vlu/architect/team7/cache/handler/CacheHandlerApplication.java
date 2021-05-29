package vlu.architect.team7.cache.handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import vlu.architect.team7.cache.handler.DTO.BusTrainDTO;
import vlu.architect.team7.cache.handler.Service.RedisService;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableEurekaClient
public class CacheHandlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheHandlerApplication.class, args);
    }
}

@Configuration
class RedisConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, BusTrainDTO> redisTemplate() {
        final RedisTemplate<String, BusTrainDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<>(BusTrainDTO.class));
        template.expire(RedisService.KEY, 1, TimeUnit.HOURS);
        return template;
    }
}