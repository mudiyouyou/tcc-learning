package com.mudiyouyou.tcc.order.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BizOrder {
    Integer oid;
    Integer uid;
    Integer status;
    Long money;
    Date payTime;
    Date createTime;
    Long benefitMoney;
}
