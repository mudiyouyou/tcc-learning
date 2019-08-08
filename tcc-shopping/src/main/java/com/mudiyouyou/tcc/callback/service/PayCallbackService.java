package com.mudiyouyou.tcc.callback.service;

import com.mudiyouyou.tcc.callback.vo.PayResult;
import org.dromara.hmily.annotation.Hmily;

public interface PayCallbackService {
    @Hmily
    void payNotify(PayResult result);
}
