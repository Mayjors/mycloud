package com.eu.manager.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name ="t_user")
public class UserDomain {
    @Id
    private Integer userId;

    private String userName;

    private String password;

    private String phone;

}
