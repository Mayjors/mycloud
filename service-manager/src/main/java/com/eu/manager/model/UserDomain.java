package com.eu.manager.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserDomain {
    @Id
    private Integer userId;

    private String userName;

    private String password;

    private String phone;

}
