package com.example.lyl.redis_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean(name = "stringObjectRedisTemplate")
    public RedisTemplate<String, Object> stringObjectRedisTemplate(LettuceConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> stringObjectRedisTemplate = new RedisTemplate<>();
        stringObjectRedisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        stringObjectRedisTemplate.setStringSerializer(StringRedisSerializer.UTF_8);
        stringObjectRedisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
        stringObjectRedisTemplate.setHashKeySerializer(StringRedisSerializer.UTF_8);
        stringObjectRedisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        stringObjectRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        return stringObjectRedisTemplate;
    }
}
