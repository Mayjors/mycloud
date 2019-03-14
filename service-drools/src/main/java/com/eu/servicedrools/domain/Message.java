package com.eu.servicedrools.domain;

import lombok.Data;

/**
 * @author yuanjie
 * @date 2019/3/14 14:30
 */
@Data
public class Message {
    public static final Integer HELLO = 0;
    public static final Integer GOODBYE = 1;

    private String message;

    private Integer status;
}
