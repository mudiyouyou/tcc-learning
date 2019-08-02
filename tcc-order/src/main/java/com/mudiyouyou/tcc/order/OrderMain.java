package com.mudiyouyou.tcc.order;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableDubboConfig
@DubboComponentScan(basePackages = "com.mudiyouyou.tcc.order.service.impl")
public class OrderMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.main(args);
    }
}
