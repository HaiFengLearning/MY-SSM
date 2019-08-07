package net.wanho.mapper;

import net.wanho.po.User;

import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */
public interface UserMapper {


    //注册
    void register(User user);
    //myrealm 登录认证查找用户名是否存在
    User getUserByName(String userName);

    //用户界面 展示用户信息
    List<User> selectAllUser();

    //更改状态
    void updateStatus(User user);


}
