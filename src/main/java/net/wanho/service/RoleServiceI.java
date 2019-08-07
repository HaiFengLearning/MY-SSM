package net.wanho.service;

import net.wanho.po.Role;
import net.wanho.po.RolePower;

import java.util.List;

/**
 * Created by Administrator on 2019/8/1.
 */
public interface RoleServiceI {

    List<Role> selectAllRole(String status);
    List<Role> selectAllRoleAndAllStatus();
    List<Role> selectRoleById(Integer id);

    void updateUserRole(Integer id,Integer[] roleId);

    void delRoleStatus(Integer roleId);
    void  updateRoleStatus(Integer roleId);

    void  updateRolePower(RolePower rolePower);

    void insertRole(String roleName);

}
