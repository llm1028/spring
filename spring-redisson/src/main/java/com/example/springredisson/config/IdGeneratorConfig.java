package com.example.springredisson.config;

import com.gome.architect.idgnrt.IDGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liluming
 * @className: IdGeneratorConfig
 * @description:
 * @date 2022/6/9 4:19 下午
 */
@Configuration
public class IdGeneratorConfig {
    @Bean
    public IDGenerator idGenerator() {
        IDGenerator idGenerator = new IDGenerator();
        idGenerator.setZkServers("10.112.179.149:2181,10.112.179.150:2181,10.112.179.151:2181");
        idGenerator.setSeqName("test.table.col2");
        idGenerator.init();
        return idGenerator;
    }
}
