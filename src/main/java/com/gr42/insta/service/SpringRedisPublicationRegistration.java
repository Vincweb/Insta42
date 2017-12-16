package com.gr42.insta.service;

import java.util.HashMap;
import java.util.Map;

import com.gr42.insta.model.Publication;
import com.gr42.insta.util.SpringRedisConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

public class SpringRedisPublicationRegistration {

    public SpringRedisPublicationRegistration(Publication pub) {

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);
        try {
            StringRedisTemplate redisTemplate = (StringRedisTemplate) ctx.getBean("strRedisTemplate");
            HashOperations<String, String, String> hash = redisTemplate.opsForHash();
            String idkey = "pub:"+ hash.increment("pub:", "pub:", 1);

            Map<String, String> memMap = new HashMap<>();
            memMap.put("id", pub.getId().toString());
            memMap.put("imageName", pub.getImageName());
            memMap.put("comment", pub.getComment());
            memMap.put("image", pub.getImage());

            hash.putAll(idkey, memMap);

        } finally {
            ctx.close();
        }
    }
}
