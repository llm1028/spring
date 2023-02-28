package com.example.springplug.config;

import com.example.springplug.conversion.StringToUserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ConversionServiceFactoryBean;

import java.util.Collections;

/**
 * @author liluming
 * @className: AppConfig
 * @description:
 * @date 2021/12/16 2:52 下午
 */
@ComponentScan("com.example.springplug")
public class AppConfig {

    /**
     * 向Spring中注册ConversionService
     *
     * @return
     */
    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(Collections.singleton(new StringToUserConverter()));

        return conversionServiceFactoryBean;
    }
}
