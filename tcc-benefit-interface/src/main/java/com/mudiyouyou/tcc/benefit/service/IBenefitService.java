package com.mudiyouyou.tcc.benefit.service;

import org.dromara.hmily.annotation.Hmily;

public interface IBenefitService {
    @Hmily
    void reduce(Integer uid,Long amount);
}
