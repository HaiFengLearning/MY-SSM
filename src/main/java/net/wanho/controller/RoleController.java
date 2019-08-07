package net.wanho.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.wanho.po.Power;
import net.wanho.po.Role;
import net.wanho.po.RolePower;
import net.wanho.po.User;
import net.wanho.service.PowerServiceI;
import net.wanho.service.RoleServiceI;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.roles;

/**
 * Created by Administrator on 2019/8/2.
 */
@Controller
public class RoleController {

    @Autowired
    private RoleServiceI roleServiceI;

    @Autowired
    private PowerServiceI powerServiceI;

    /**
     * 查询所有角色
     * @param pageNum
     * @param map
     * @return
     */
    @RequestMapping("selectAllRole")
    public String selectAllUser(@RequestParam(defaultValue = "1") Integer pageNum, Map map){
        PageHelper.startPage(pageNum,3);
        List<Role> roles = roleServiceI.selectAllRoleAndAllStatus();
        PageInfo<Role> pageInfo = new PageInfo<Role>(roles);
        map.put("pageInfo",pageInfo);
        return "role";
    }

    /**
     * 逻辑删除角色
     * @param roleId
     * @param map
     * @return
     */
    @RequestMapping("delRoleStatus")
    public String delRoleStatus(Integer roleId,Map map){
        roleServiceI.delRoleStatus(roleId);
        return "redirect:selectAllRole";
    }
    /**
     * 启用角色
     * @param roleId
     * @param map
     * @return
     */
    @RequestMapping("updateRoleStatus")
    public String updateRoleStatus(Integer roleId,Map map){
        roleServiceI.updateRoleStatus(roleId);
        return "redirect:selectAllRole";
    }

    /**
     * 修改角色的数据回显
     * @param roleId
     * @return
     */
    @RequestMapping("updateRole")
    @ResponseBody
    public List<Map<String, Object>> updateRole(@RequestParam("roleId") Integer roleId){
//        List<Power> powers = powerServiceI.selectAllPower();
        List<Map<String, Object>> maps = powerServiceI.selectPowerById(roleId);
        return maps;
    }

    /**
     * 修改角色权限的保存
     * @param rolePower
     * @return
     */
    @RequestMapping("updatePower")
    @ResponseBody
    public String updatePower(@RequestBody RolePower rolePower){
        System.out.println(rolePower);
        roleServiceI.updateRolePower(rolePower);
        return "success";
    }

    /**
     * 增加角色
     * @param
     * @return
     */
    @RequestMapping("insertRole")

    public String insertRole(@RequestBody Role role){
        String roleName = role.getRoleName();
        System.out.println(roleName);
        roleServiceI.insertRole(roleName);
        return "role";
    }

}
