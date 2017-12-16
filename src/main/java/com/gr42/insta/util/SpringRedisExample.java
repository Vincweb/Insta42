package com.gr42.insta.util;

import java.net.URISyntaxException;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.gr42.insta.model.Employee1;

public class SpringRedisExample {
	
    @Inject
    private Logger log;

	
	public SpringRedisExample() {
		// TODO Auto-generated constructor stub
	}
	public  void test() throws URISyntaxException, Exception {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringRedisConfig.class);
		try {			
			RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
			ValueOperations values = redisTemplate.opsForValue();
			values.set("zouzou", new Employee1("02", "zouzou"));
			
			 
		} finally {
			ctx.close();
		}
	}
}
