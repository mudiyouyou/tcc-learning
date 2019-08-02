package com.mudiyouyou.tcc.order.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mudiyouyou.tcc.order.IOrderService;
import com.mudiyouyou.tcc.order.vo.BizOrder;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.annotation.PatternEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public BizOrder getOrderById(Integer oid) {
        return jdbcTemplate.queryForObject("SELECT * FROM tcc_order WHERE id = ?",BizOrder.class,oid);
    }

    /**
     * change status to "prepaying" that equals to 2, freeze the order.
     * @param oid
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Hmily(confirmMethod = "applyPayCommit",cancelMethod = "applyPayCancel",pattern = PatternEnum.TCC)
    @Override
    public void applyPay(Integer oid) {
        jdbcTemplate.update("UPDATE tcc_order set status=2 WHERE id = ?",oid);
    }

    /**
     * change status to "paying" if applying pay success.
     * @param oid
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void applyPayCommit(Integer oid) {
        jdbcTemplate.update("UPDATE tcc_order set status=3 WHERE id = ?",oid);
    }

    /**
     * change status to "close" if applying pay failed.
     * @param oid
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void applyPayCancel(Integer oid) {
        jdbcTemplate.update("UPDATE tcc_order set status=6 WHERE id = ?",oid);
    }

    /**
     * status  1=waiting for pay,2=prepaying,3=paying,4=paid,5=pay failed,6=close
     * @param uid
     * @param money
     * @param benefitMoney
     */
    @Override
    public void create(Integer uid, Long money,Long benefitMoney) {
        jdbcTemplate.update("INSERT INTO tcc_order(uid,status,money,benefitMoney) VALUES (?,?,?,?)",
                uid,1,money,benefitMoney);
    }

}
