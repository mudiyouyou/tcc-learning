package com.mudiyouyou.tcc.order.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mudiyouyou.tcc.order.IOrderService;
import com.mudiyouyou.tcc.order.vo.BizOrder;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.annotation.PatternEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public BizOrder getOrderById(Integer oid) {
        return jdbcTemplate.queryForObject("SELECT * FROM tcc_order WHERE id = ?",
                new RowMapper<BizOrder>() {
                    @Override
                    public BizOrder mapRow(ResultSet resultSet, int i) throws SQLException {
                        BizOrder order = new BizOrder();
                        order.setId(resultSet.getInt("id"));
                        order.setUid(resultSet.getInt("uid"));
                        order.setStatus(resultSet.getInt("status"));
                        order.setMoney(resultSet.getLong("money"));
                        order.setPayTime(resultSet.getDate("payTime"));
                        order.setCreateTime(resultSet.getDate("createTime"));
                        order.setBenefitMoney(resultSet.getLong("benefitMoney"));
                        return order;
                    }
                }, oid);
    }

    /**
     * change status to "prepaying" that equals to 2, freeze the order.
     *
     * @param oid
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Hmily(confirmMethod = "applyPayCommit", cancelMethod = "applyPayCancel", pattern = PatternEnum.TCC)
    @Override
    public void applyPay(Integer oid) {
        BizOrder currentOrder = this.getOrderById(oid);
        if (currentOrder == null) {
            throw new RuntimeException("Not exist this order");
        }
        if(currentOrder.getStatus()==3 || currentOrder.getStatus()==6){
            throw new RuntimeException("Finish already");
        }
        jdbcTemplate.update("UPDATE tcc_order set status=2 WHERE id = ? AND status = 1", oid);
    }

    /**
     * change status to "paying" if applying pay success.
     *
     * @param oid
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void applyPayCommit(Integer oid) {
        jdbcTemplate.update("UPDATE tcc_order set status=3,payTime=? WHERE id = ? AND status = 2",new Date(), oid);
    }

    /**
     * change status to "close" if applying pay failed.
     *
     * @param oid
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void applyPayCancel(Integer oid) {
        jdbcTemplate.update("UPDATE tcc_order set status = 6 WHERE id = ? AND status = 2", oid);
    }

    /**
     * status  1=waiting for pay,2=prepaying,3=paying,4=paid,5=pay failed,6=close
     *
     * @param uid
     * @param money
     * @param benefitMoney
     */
    @Override
    public void create(Integer uid, Long money, Long benefitMoney) {
        jdbcTemplate.update("INSERT INTO tcc_order(uid,status,money,benefitMoney) VALUES (?,?,?,?)",
                uid, 1, money, benefitMoney);
    }

}
