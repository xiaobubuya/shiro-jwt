package com.xiaobubuya.shiroJwt.model;


import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 *
 * @Author 陶芃宇
 * @Description user
 * @Date 2020-11-17
 * @Time 16:25
 */
@Data
public class UserInfo implements Serializable {
    private int id;
    private String username;
    private String password;
    private int ban;
    private String role;
    private String permission;

}
