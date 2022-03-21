package com.example.springredisson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author liluming
 * @className: RedisClusterConfig
 * @description:
 * @date 2022/3/18 6:24 下午
 */
@Configuration
public class RedisClusterConfig {
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        // 构建template
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(factory);
        // 设置key序列化方式为字符串
        template.setKeySerializer(new StringRedisSerializer());
        // 设置value序列化方式为JSON
        template.afterPropertiesSet();
        return template;
    }
}
