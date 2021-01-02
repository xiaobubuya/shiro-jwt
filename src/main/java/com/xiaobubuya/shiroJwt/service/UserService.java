package com.xiaobubuya.shiroJwt.service;



import com.xiaobubuya.shiroJwt.base.Result;
import com.xiaobubuya.shiroJwt.model.UserInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author 陶芃宇
 * @Description
 * @Date 2020-11-17
 * @Time 16:25
 */
public interface UserService {
    public Result regist(UserInfo userInfo);

    public Result login(UserInfo userInfo);

    public String getPassword(String username);

    public int checkUserBanStatus(String username);

    public String getRole(String username);

    public String getRolePermission(String username);

    public String getPermission(String username);

    public List<UserInfo> getAllUser();

    public void banUser(String username);
}


