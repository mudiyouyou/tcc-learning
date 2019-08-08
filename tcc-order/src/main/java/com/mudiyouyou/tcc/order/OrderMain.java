package com.mudiyouyou.tcc.order;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.util.concurrent.TimeUnit;

@DubboComponentScan(basePackages = {"com.mudiyouyou.tcc.order.service"})
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class OrderMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(OrderMain.class);
    }
}
