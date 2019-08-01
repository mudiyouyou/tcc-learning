package com.mudiyouyou.tcc.order;

import com.mudiyouyou.tcc.order.vo.BizOrder;
import org.dromara.hmily.annotation.Hmily;

public interface IOrderService {

    BizOrder getOrderById(String oid);

    @Hmily
    void updateSuccess(String oid);

    @Hmily
    void updateFail(String oid);
}
