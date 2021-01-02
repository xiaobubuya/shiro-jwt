package com.xiaobubuya.shiroJwt.Mapper;

import com.xiaobubuya.shiroJwt.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author 陶芃宇
 * @Description
 * @Date 2020-11-17
 * @Time 16:25
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user values (#{id},#{username},#{password})")
    public void create(UserInfo userInfo);

    @Select("select * from user where username = #{username}")
    UserInfo findUserByusername(String username);

    @Select("select role.permission from user,role where username = #{username} and role.role = user.role")
    String getRolePermission(String username);

    @Select("select * from user")
    List<UserInfo> findAll();

    @Update("update user set ban = 1 where username = #{username}")
    public void banuser(String username);
}
