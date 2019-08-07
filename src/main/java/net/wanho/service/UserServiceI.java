package net.wanho.service;

import net.wanho.po.User;

import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */
public interface UserServiceI {
    void  register(User user);

    User getUserByName(String userName);

    //查询用户
    List<User> selectAllUser();

    //用户禁用
    void updateStatus(User user);

    //删除用户
    void delUser(User user);
}
