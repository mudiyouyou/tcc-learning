package com.mudiyouyou.tcc.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
public class CallbackMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.main(args);
        log.info("Callback is running");
    }
}
