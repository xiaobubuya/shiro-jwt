package com.xiaobubuya.shiroJwt.service;

import com.xiaobubuya.shiroJwt.Mapper.UserMapper;
import com.xiaobubuya.shiroJwt.base.Result;
import com.xiaobubuya.shiroJwt.model.UserInfo;
import com.xiaobubuya.shiroJwt.util.JWTUtil;
import com.xiaobubuya.shiroJwt.util.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author 陶芃宇
 * @Description
 * @Date 2020-11-17
 * @Time 16:25
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;


    @Override
    public Result regist(UserInfo userInfo) {
        if(userInfo.getUsername().equals("")|| userInfo.getPassword().equals("")) {
            return ResultFactory.failure("请输入账号或密码");
        }
        UserInfo existUserInfo = userMapper.findUserByusername(userInfo.getUsername());
        if(existUserInfo !=null){
            return ResultFactory.failure("用户名已存在");
        }
        else{
            userMapper.create(userInfo);
            UserInfo realUserInfo = userMapper.findUserByusername(userInfo.getUsername());
            return ResultFactory.successWithData(realUserInfo,"注册成功");
        }

    }

    @Override
    public Result login(UserInfo userInfo) {
        UserInfo existUserInfo = userMapper.findUserByusername(userInfo.getUsername());
        if(existUserInfo ==null){
            return ResultFactory.failure("用户名不存在");
        }
        else if (!existUserInfo.getPassword().equals(userInfo.getPassword())){
            return ResultFactory.failure("密码与用户名不匹配");
        }
        else {
            String token = JWTUtil.createToken(existUserInfo.getUsername());
            System.out.println(token);
            return ResultFactory.successWithData(existUserInfo,token);
        }
    }

    @Override
    public String getPassword(String username) {
        UserInfo existUserInfo = userMapper.findUserByusername(username);
        return existUserInfo.getPassword();

    }

    @Override
    public int checkUserBanStatus(String username){
        UserInfo existUserInfo = userMapper.findUserByusername(username);
        return existUserInfo.getBan();
    }

    @Override
    public String getRole(String username){
        UserInfo thisRole = userMapper.findUserByusername(username);
        return thisRole.getRole();
    }

    @Override
    public String getRolePermission(String username){
        String thisRolePermission = userMapper.getRolePermission(username);
        return thisRolePermission;
    }

    @Override
    public String getPermission(String username){
        UserInfo thisPermission = userMapper.findUserByusername(username);
        return thisPermission.getPermission();
    }

    @Override
    public List<UserInfo> getAllUser(){
        List<UserInfo> AllUser = userMapper.findAll();
        return AllUser;
    }

    @Override
    public void banUser(String username){
        userMapper.banuser(username);
    }

}
