package vlu.architect.team7.cache.Controller;

import net.spy.memcached.MemcachedClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @Autowired
    private MemcachedClient client;

    @GetMapping("/get/{key}")
    public String get(@PathVariable String key) {
        Object value = client.get(key);
        return value == null ? null : value.toString();
    }

    @PostMapping("/set")
    public void set(@RequestParam String key, @RequestParam int exp, @RequestBody String content) {
        LoggerFactory.getLogger(MainController.class).error("set cache called with " + key + "-" + exp + "-" + content);
        client.set(key, exp, content);
    }
}
