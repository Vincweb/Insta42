package com.gr42.insta.service;

import java.util.HashMap;
import java.util.Map;

import com.gr42.insta.model.Member;
import com.gr42.insta.util.SpringRedisConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

public class SpringRedisMemberRegistration {

	public SpringRedisMemberRegistration(Member member) {

		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringRedisConfig.class);
		try {
			StringRedisTemplate redisTemplate = (StringRedisTemplate) ctx.getBean("strRedisTemplate");
			HashOperations<String, String, String> hash = redisTemplate.opsForHash();
			String idkey = "user:" + member.getId().toString();

			Map<String, String> memMap = new HashMap<>();
			memMap.put("id", member.getId().toString());
			memMap.put("name", member.getName());
			memMap.put("email", member.getEmail());
			memMap.put("phoneNumber", member.getPhoneNumber());

			hash.putAll(idkey, memMap);

		} finally {
			ctx.close();
		}
	}
}
