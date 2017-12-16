package com.gr42.insta.util;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;



public class SpringRedisExample {
	
    @Inject
    private Logger log;

	
	public SpringRedisExample() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public  void test(Long id,String name ,String email ,String phone ) throws URISyntaxException, Exception {
		
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringRedisConfig.class);
		try {	
			String id1 = id.toString();
			StringRedisTemplate redisTemplate = (StringRedisTemplate) ctx.getBean("strRedisTemplate");
			HashOperations<String, String, String> hash = redisTemplate.opsForHash();
			String idkey = id1;
			
			Map<String, String> memMap = new HashMap<>();
			memMap.put("name", name);
			memMap.put("email", email);
			memMap.put("phoneNumber", phone);
			
			
			hash.putAll(idkey, memMap);
			
			System.out.println("Get emp joe details: " + hash.entries(idkey));
		
		} finally {
			ctx.close();
		}
	}
}














