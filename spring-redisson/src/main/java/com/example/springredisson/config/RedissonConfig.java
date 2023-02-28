package com.example.springredisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author liluming
 * @className: RedissonConfig
 * @description:
 * @date 2022/3/18 5:14 下午
 */
@Configuration
public class RedissonConfig {

    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() throws IOException {
        //1、创建配置
        Config config = new Config();
        config.useClusterServers()
                .setPassword("liluming")
                .addNodeAddress("redis://10.115.4.112:8001", "redis://10.115.4.112:8002", "redis://10.115.4.112:8003");
        // .addNodeAddress("redis://10.115.4.112:8002");
        return Redisson.create(config);
    }

}