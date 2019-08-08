package com.mudiyouyou.tcc.order;

import com.mudiyouyou.tcc.order.vo.BizOrder;
import org.dromara.hmily.annotation.Hmily;

public interface IOrderService {

    BizOrder getOrderById(Integer oid);
    @Hmily
    void applyPay(Integer oid);

    void create(Integer uid, Long money,Long benefitMoney);
}
