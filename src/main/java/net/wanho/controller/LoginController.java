package net.wanho.controller;

import net.wanho.po.User;
import net.wanho.service.UserServiceI;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.security.auth.login.AccountException;

/**
 * Created by Administrator on 2019/7/30.
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserServiceI userServiceI;

    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("register")
    public String register(User user){
        String viewName = "fail";
        try {
            User userByName = userServiceI.getUserByName(user.getUserName());
            if (userByName!=null){
                throw new AccountException("账号已存在");
            }
            userServiceI.register(user);
            viewName = "redirect:/login/toLogin";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewName;
    }

    /**
     * 登录
     * @param user
     * @param rememberMe
     * @return
     */
    @RequestMapping("check")
    public String check(User user,boolean rememberMe){
        String viewName = "fail";


        if (user==null){
            throw  new RuntimeException("参数不能为空");
        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
            //是否记住我
            token.setRememberMe(rememberMe);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            //具体方法处于shiro里面
            viewName = "index";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewName;
    }
    @RequestMapping("toIndex")
    public String toIndex(){
        return "index";

    }
}
