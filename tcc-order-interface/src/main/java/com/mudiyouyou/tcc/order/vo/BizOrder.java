package com.mudiyouyou.tcc.order.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BizOrder implements Serializable{
    private static final long serialVersionUID = -6381247318213718222L;
    Integer id;
    Integer uid;
    Integer status;
    Long money;
    Date payTime;
    Date createTime;
    Long benefitMoney;
}
