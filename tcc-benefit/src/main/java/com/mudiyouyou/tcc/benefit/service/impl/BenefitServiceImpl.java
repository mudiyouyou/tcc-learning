package com.mudiyouyou.tcc.benefit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mudiyouyou.tcc.benefit.service.IBenefitService;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class BenefitServiceImpl implements IBenefitService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Hmily(confirmMethod = "reduceCommmit", cancelMethod = "reduceCancel")
    @Override
    public void reduce(Integer uid, Long amount) {
        jdbcTemplate.update("UPDATE tcc_benefit set amount = amount - ?,per_amount=pre_amount + ? WHERE uid = ?"
                , uid, amount);
    }

    public void reduceCommmit(Integer uid, Long amount) {
        jdbcTemplate.update("UPDATE tcc_benefit set per_amount = pre_amount - ? WHERE uid = ?"
                , uid, amount);
    }

    public void reduceCancel(Integer uid, Long amount) {
        jdbcTemplate.update("UPDATE tcc_benefit set amount = amount + ?,per_amount=pre_amount - ? WHERE uid = ?"
                , uid, amount);
    }
}
