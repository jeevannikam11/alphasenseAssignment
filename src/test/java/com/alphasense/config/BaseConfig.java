package com.alphasense.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;

@SpringBootApplication
@Configuration
@PropertySource(value = "classpath:/config/qa/application.yml", ignoreResourceNotFound = false)
@ContextConfiguration(classes = BaseConfig.class, loader = SpringBootContextLoader.class)
@ComponentScan(basePackages = {"com.alphasense.pojoClasses", "com.alphasense.stepDef", "com.alphasense.stepDefHelper"})
public class BaseConfig {
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
