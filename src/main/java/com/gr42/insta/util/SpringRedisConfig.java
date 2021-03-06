package com.gr42.insta.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class SpringRedisConfig {
	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setHostName("192.168.99.100");
		connectionFactory.setPort(6379);
		return connectionFactory;
	}

	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(connectionFactory());
                redisTemplate.setKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}
}