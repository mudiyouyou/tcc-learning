package com.mudiyouyou.tcc.callback;


import com.alibaba.dubbo.config.annotation.Reference;
import com.mudiyouyou.tcc.callback.service.PayCallbackService;
import com.mudiyouyou.tcc.callback.vo.PayResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingTest {
    @Reference(injvm = true)
    PayCallbackService payCallbackService;
    @Test
    public void testPayCallbackSucess() throws InterruptedException {
        PayResult result = new PayResult();
        result.setMerOrderId(3);
        result.setPayStatus(1);
        payCallbackService.payNotify(result);
        TimeUnit.SECONDS.sleep(5);
    }
}
