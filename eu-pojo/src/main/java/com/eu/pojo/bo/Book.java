package com.eu.pojo.bo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author yuanjie
 * @date 2018/12/26 16:34
 */
@Data
public class Book {
    @Id
    private long id;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 书名
     */
    private String name;

    /**
     * 简介
     */
    private String info;

    /**
     * 出版社
     */
    private String publish;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
