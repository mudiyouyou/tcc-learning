package com.mudiyouyou.tcc.benefit;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.util.concurrent.TimeUnit;

@DubboComponentScan(basePackages = "com.mudiyouyou.tcc.benefit.service")
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class BenefitMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BenefitMain.class);
        while (true) {
            TimeUnit.HOURS.sleep(1);
        }
    }
}
