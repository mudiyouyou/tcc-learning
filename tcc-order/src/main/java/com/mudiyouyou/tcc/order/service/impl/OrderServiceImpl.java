package com.mudiyouyou.tcc.order.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mudiyouyou.tcc.order.IOrderService;
import com.mudiyouyou.tcc.order.vo.BizOrder;
import org.dromara.hmily.annotation.Hmily;
@Service
public class OrderServiceImpl implements IOrderService {
    @Override
    public BizOrder getOrderById(String oid) {
        return null;
    }

    @Hmily(confirmMethod = "",cancelMethod = "")
    @Override
    public void updateSuccess(String oid) {

    }

    private void successCommit(String oid) {

    }
    private void successCancel(String oid) {

    }

    @Hmily(confirmMethod = "",cancelMethod = "")
    @Override
    public void updateFail(String oid) {

    }

    private void failCommit(String oid) {

    }

    private void failCancel(String oid) {

    }
}
