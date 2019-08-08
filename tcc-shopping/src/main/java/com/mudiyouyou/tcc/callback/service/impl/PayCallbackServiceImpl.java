package com.mudiyouyou.tcc.callback.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.mudiyouyou.tcc.benefit.service.IBenefitService;
import com.mudiyouyou.tcc.callback.service.PayCallbackService;
import com.mudiyouyou.tcc.callback.vo.PayResult;
import com.mudiyouyou.tcc.order.IOrderService;
import com.mudiyouyou.tcc.order.vo.BizOrder;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;

@Slf4j
@Service
public class PayCallbackServiceImpl implements PayCallbackService {
    @Reference
    IOrderService orderService;
    @Reference
    IBenefitService benefitService;

    @Hmily(confirmMethod = "commit",cancelMethod = "cancel")
    @Override
    public void payNotify(PayResult result) {
        // if status == 1
        if (result.getPayStatus() == 1) {
            BizOrder bizOrder = orderService.getOrderById(result.getMerOrderId());
            // update merchant order to paid
            orderService.applyPay(result.getMerOrderId());
            // reduce benefit of user
            benefitService.reduce(bizOrder.getUid(), bizOrder.getBenefitMoney());
            // notify message to user
        }
    }

    public void commit(PayResult result) {
        log.info(this.getClass().getName() + ".commit");
    }

    public void cancel(PayResult result) {
        log.error(this.getClass().getName() + ".cancel");
    }
}
