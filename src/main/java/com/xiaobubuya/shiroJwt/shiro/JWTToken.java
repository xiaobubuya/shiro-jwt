package com.xiaobubuya.shiroJwt.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created with IntelliJ IDEA
 *
 * @Author 陶芃宇
 * @Description 登陆注册及权限测试
 * @Date 2020-11-17
 * @Time 16:25
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
