package com.xiaobubuya.shiroJwt.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA
 *
 * @Author 陶芃宇
 * @Description 接口返回对象
 * @Date 2020-11-17
 * @Time 22:25
 */
@Component
public class MyResultMap extends HashMap<String, Object> {
    public MyResultMap() {
    }

    public MyResultMap success() {
        this.put("result", "success");
        return this;
    }

    public MyResultMap fail() {
        this.put("result", "fail");
        return this;
    }

    public MyResultMap code(int code) {
        this.put("code", code);
        return this;
    }

    public MyResultMap message(Object message) {
        this.put("message", message);
        return this;
    }
}

