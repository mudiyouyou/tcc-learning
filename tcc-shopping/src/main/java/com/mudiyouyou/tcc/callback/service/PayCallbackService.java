package com.mudiyouyou.tcc.callback.service;

import com.mudiyouyou.tcc.callback.vo.PayResult;

public interface PayCallbackService {
    void payNotify(PayResult result);
}
