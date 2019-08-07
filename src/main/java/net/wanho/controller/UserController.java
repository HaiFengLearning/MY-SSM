package net.wanho.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.wanho.mapper.RoleMapper;
import net.wanho.po.Role;
import net.wanho.po.User;
import net.wanho.service.RoleServiceI;
import net.wanho.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/1.
 */
@Controller
public class UserController {

    @Autowired
    private UserServiceI userServiceI;

    @Autowired
    private RoleServiceI roleServiceI;

    /**
     * 用户列表
     * @param pageNum
     * @param map
     * @return
     */
    @RequestMapping("select")
    public String selectAllUser(@RequestParam(defaultValue = "1") Integer pageNum, Map map){
        PageHelper.startPage(pageNum,3);
        List<User> users = userServiceI.selectAllUser();
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        map.put("pageInfo",pageInfo);
        return "user";

    }

    /**
     * 修改用户状态
     * @param userName
     * @return
     */
    @RequestMapping("updateStatus")
    public String updateStatus(@RequestParam("userName") String userName){
        User userByName = userServiceI.getUserByName(userName);
        System.out.println(userByName);

        userServiceI.updateStatus(userByName);
        return "redirect:select";

    }

    /**
     * 删除用户 逻辑删
     * @param userName
     * @return
     */
    @RequestMapping("delUser")
    public String delStu(@RequestParam("userName") String userName){
        User userByName = userServiceI.getUserByName(userName);
        userServiceI.delUser(userByName);
        return "redirect:select";

    }

    /**
     * 修改初始化 用户界面修改  用户角色
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("updateInit")
    public String  updateInit(@RequestParam("id") Integer id,Map map){
        List<Role> roles = roleServiceI.selectAllRole("正常");
        map.put("role",roles);
        List<Role> roleById = roleServiceI.selectRoleById(id);
        map.put("myRole",roleById);
        map.put("id",id);
        return "userUpdate";
    }

    /**
     * 修改保存
     * @param roleId
     * @param id
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public String  update(@RequestParam("roleId") Integer[] roleId,@RequestParam("id") Integer id){
        System.out.println("执行修改方法");
        roleServiceI.updateUserRole(id,roleId);
        String msg = "scusses";
        return msg;
    }

}
