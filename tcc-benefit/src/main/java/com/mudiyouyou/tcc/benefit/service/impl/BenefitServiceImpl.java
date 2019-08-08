package com.mudiyouyou.tcc.benefit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mudiyouyou.tcc.benefit.service.IBenefitService;
import com.mudiyouyou.tcc.benefit.vo.BenefitVo;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class BenefitServiceImpl implements IBenefitService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = TransactionException.class)
    @Hmily(confirmMethod = "reduceCommmit", cancelMethod = "reduceCancel")
    @Override
    public void reduce(Integer uid, Long amount) {
        BenefitVo benefit = jdbcTemplate.queryForObject("select amount from tcc_benefit where uid = ?", new RowMapper<BenefitVo>() {
            @Override
            public BenefitVo mapRow(ResultSet resultSet, int i) throws SQLException {
                BenefitVo vo = new BenefitVo();
                vo.setAmount(resultSet.getLong("amount"));
                return vo;
            }
        }, uid);
        if (benefit.getAmount() == null || benefit.getAmount() < amount) {
            return;
        }
        jdbcTemplate.update("UPDATE tcc_benefit set amount = amount - ?,pre_amount = pre_amount + ? WHERE uid = ?"
                , amount, amount, uid);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void reduceCommmit(Integer uid, Long amount) {
        jdbcTemplate.update("UPDATE tcc_benefit set pre_amount = pre_amount - ? WHERE uid = ? AND pre_amount >= ?"
                , amount, uid , amount);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void reduceCancel(Integer uid, Long amount) {
        jdbcTemplate.update("UPDATE tcc_benefit set amount = amount + ?,pre_amount=pre_amount - ? WHERE uid = ? AND pre_amount >= ?"
                , amount, amount, uid, amount);
    }
}
