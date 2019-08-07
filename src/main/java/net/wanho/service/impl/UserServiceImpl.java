package net.wanho.service.impl;

import net.wanho.mapper.UserMapper;
import net.wanho.po.User;
import net.wanho.service.UserServiceI;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2019/7/30.
 */
@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册 密码加密 MD5
     * @param user
     */
    @Override
    public void register(User user) {
        //密码加密
        if (user==null){
            throw  new RuntimeException("参数不能为空");
        }
        user.setStatus("禁用");
        //取随机数
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        user.setPassword(shiroMD5(user.getPassword(),salt));
        userMapper.register(user);

    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

//    @Override
//    public User getUserByName(String userName) {
//        return userMapper.getUserByName(userName);
//    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    /**
     * 更改用户状态 禁用
     */
    @Override
    public void updateStatus(User user) {
        if ("正常".equals(user.getStatus())){
            user.setStatus("禁用");
        }else{
            user.setStatus("正常");
        }
        userMapper.updateStatus(user);
    }

    @Override
    public void delUser(User user) {
        if ("正常".equals(user.getStatus())){
            user.setStatus("禁用");
        }
        userMapper.updateStatus(user);
    }

    /**
     * 密码加密 返回加密后的字符串
     * @param password
     * @param salt
     * @return
     */
    public String shiroMD5(String password,String salt){
        String hashAlgorithmName = "MD5";
        int hashIterations = 2 ;

        //把salt转成ByteSource
        ByteSource saltSource = ByteSource.Util.bytes(salt);

        Object object = new SimpleHash(hashAlgorithmName, password, saltSource, hashIterations);
        return object.toString();
    }
}
