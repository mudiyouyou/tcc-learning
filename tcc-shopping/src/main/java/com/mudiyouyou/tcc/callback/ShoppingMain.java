package com.mudiyouyou.tcc.callback;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@DubboComponentScan("com.mudiyouyou.tcc.callback.service")
public class ShoppingMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ShoppingMain.class);
        log.info("Shopping is running");
    }
}
