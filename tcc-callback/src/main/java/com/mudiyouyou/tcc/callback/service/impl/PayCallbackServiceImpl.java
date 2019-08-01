package com.mudiyouyou.tcc.callback.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mudiyouyou.tcc.benefit.service.IBenefitService;
import com.mudiyouyou.tcc.callback.service.PayCallbackService;
import com.mudiyouyou.tcc.callback.vo.PayResult;
import com.mudiyouyou.tcc.order.IOrderService;
import com.mudiyouyou.tcc.order.vo.BizOrder;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class PayCallbackServiceImpl implements PayCallbackService {
    @Autowired
    IOrderService orderService;
    @Autowired
    IBenefitService benefitService;
    @Override
    public void payNotify(PayResult result) {
        // if status == 1
        if (result.getPayStatus() == 1) {
            BizOrder bizOrder = orderService.getOrderById(result.getMerOrderId());
            // update merchant order to paid
            orderService.updateSuccess(result.getMerOrderId());
            // reduce benefit of user
            benefitService.reduce(bizOrder.getUid(),bizOrder.getBenefitMoney());
            // notify message to user
        }else{
            orderService.updateFail(result.getMerOrderId());
        }
    }
}
