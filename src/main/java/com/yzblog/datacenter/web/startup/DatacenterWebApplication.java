package com.yzblog.datacenter.web.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 应用启动类
 * Created by renhua.zhang on 2017/5/22.
 */
@RestController
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan("com.yzblog.datacenter.web")
public class DatacenterWebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DatacenterWebApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(DatacenterWebApplication.class, args);
    }

}
