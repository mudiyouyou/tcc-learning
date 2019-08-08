package com.mudiyouyou.tcc.benefit.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mudiyouyou.tcc.benefit.service.IBenefitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BenefitServiceImplTest {
    @Reference
    private IBenefitService benefitService;
    @Test
    public void testReduceSuccess() throws InterruptedException {
        benefitService.reduce(1,1L);
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void testReduceFail() throws InterruptedException {
        benefitService.reduce(1,10000L);
        TimeUnit.SECONDS.sleep(10);
    }
}
