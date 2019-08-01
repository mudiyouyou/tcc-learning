package com.mudiyouyou.tcc.benefit.service.impl;

import com.mudiyouyou.tcc.benefit.service.IBenefitService;
import org.dromara.hmily.annotation.Hmily;

public class BenefitServiceImpl implements IBenefitService {
    @Hmily(confirmMethod = "commit",cancelMethod = "cancel")
    @Override
    public void reduce(Integer uid, Long amount) {

    }

    private void commmit(Integer uid,Long amount){

    }

    private void cancel(Integer uid,Long amount){

    }
}
