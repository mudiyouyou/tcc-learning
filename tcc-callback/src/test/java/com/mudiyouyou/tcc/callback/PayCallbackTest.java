package com.mudiyouyou.tcc.callback;


import com.alibaba.dubbo.config.annotation.Reference;
import com.mudiyouyou.tcc.callback.service.PayCallbackService;
import com.mudiyouyou.tcc.callback.vo.PayResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayCallbackTest {
    @Reference(injvm = true)
    PayCallbackService payCallbackService;
    @Test
    public void testPayCallbackSucess(){
        PayResult result = new PayResult();
        result.setMerOrderId("1");
        result.setPayStatus(1);
        payCallbackService.payNotify(result);
    }
}
