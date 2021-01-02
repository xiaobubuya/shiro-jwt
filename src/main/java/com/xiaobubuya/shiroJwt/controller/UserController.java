package com.xiaobubuya.shiroJwt.controller;

import com.xiaobubuya.shiroJwt.base.Result;
import com.xiaobubuya.shiroJwt.model.MyResultMap;
import com.xiaobubuya.shiroJwt.model.UserInfo;
import com.xiaobubuya.shiroJwt.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA
 *
 * @Author 陶芃宇
 * @Description 登陆注册及权限测试
 * @Date 2020-11-17
 * @Time 16:25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MyResultMap myResultMap;

    @RequiresRoles(logical = Logical.OR, value = {"admin"})
    @PostMapping(value = "/register")
    public Result register(@RequestBody UserInfo userInfo){
        return userService.regist(userInfo);
    }

    @PostMapping(value = "/login")
    public Result login(@RequestBody UserInfo userInfo){
        return userService.login(userInfo);
    }

    /**
     * 拥有 user, admin 角色的用户可以访问下面的页面
     */
    @GetMapping("/getMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user","admin"})
    public MyResultMap getMessage() {
        return myResultMap.success().code(200).message("成功获得信息！");
    }
    /**
     * 拥有 vip 权限可以访问该页面
     */
    @GetMapping("/getVipMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequiresPermissions("vip")
    public MyResultMap getVipMessage() {
        return myResultMap.success().code(200).message("成功获得 vip 信息！");
    }

    /**
     * 将非法请求跳转到 /unauthorized/**
     */
    @RequestMapping(path = "/unauthorized/{message}")
    public MyResultMap unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return myResultMap.success().code(401).message(message);
    }

}
