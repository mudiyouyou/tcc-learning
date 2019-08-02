package com.mudiyouyou.tcc.benefit;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfig
@DubboComponentScan(basePackages = "com.mudiyouyou.tcc.benefit.service.impl")
public class BenefitMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.main(args);
    }
}
