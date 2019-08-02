package com.mudiyouyou.tcc.order.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mudiyouyou.tcc.order.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Reference
    IOrderService orderService;
    @Test
    public void testCreate(){
        orderService.create(1,10000L,1000L);
    }

    @Test
    public void testApplyPay() {
        orderService.applyPay(1);
    }
}
