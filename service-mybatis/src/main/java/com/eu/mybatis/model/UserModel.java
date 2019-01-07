package com.eu.mybatis.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yuanjie
 * @date 2019/1/7 14:27
 */
@Data
@Entity(name = "t_user")
public class UserModel implements Serializable {
    @Id
    private Long userId;
    private String userName;
    private String password;
    private String phone;
}
