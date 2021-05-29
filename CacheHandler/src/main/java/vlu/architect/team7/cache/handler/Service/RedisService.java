package vlu.architect.team7.cache.handler.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vlu.architect.team7.cache.handler.DTO.BusTrainDTO;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class RedisService {
    public static final String KEY = "Ticket";

    @Autowired
    private RedisTemplate<String, BusTrainDTO> redisTemplate;
    private ListOperations<String, BusTrainDTO> listOperations;

    @PostConstruct
    private void initialize() {
        listOperations = redisTemplate.opsForList();
    }

    public List<BusTrainDTO> getAll() throws Exception {
        Long length = listOperations.size(KEY);
        if (length == null)
            throw new Exception("Something went wrong");
        return listOperations.range(KEY, 0, length);
    }

    public void addItemAll(List<BusTrainDTO> items) {
        listOperations.rightPushAll(KEY, items);
    }
}
