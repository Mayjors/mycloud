package com.eu.api.config;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yuanjie
 * @date 2019/1/10 19:24
 */
@Data
public class WxpayParam {

    /**
     * 微信支付的金额是String类型， 并且是以分为单位
     */
    BigDecimal totalPrice = new BigDecimal(1);  //此时的单位是元

    private String body = "xxx等商品信息";
    private String totalFee = totalPrice.multiply(new BigDecimal(100)).toBigInteger().toString();
    /**
     * 随机数字字符串
     */
    private String outTradeNo = "4784984230432842944";


}
