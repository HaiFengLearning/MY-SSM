package net.wanho.service.impl;

import net.wanho.mapper.RoleMapper;
import net.wanho.po.Role;
import net.wanho.po.RolePower;
import net.wanho.service.RoleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/8/1.
 */
@Service
public class RoleServiceImpl implements RoleServiceI{

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> selectAllRole(String status) {
        return roleMapper.selectAllRole(status);
    }

    @Override
    public List<Role> selectAllRoleAndAllStatus() {
        return roleMapper.selectAllRoleAndAllStatus();
    }

    @Override
    public List<Role> selectRoleById(Integer id) {
        return roleMapper.selectRoleById(id);
    }

    @Override
    public void updateUserRole(Integer id, Integer[] roleId) {

        roleMapper.delRole(id);
        for (int i = 0 ; i<roleId.length;i++){
            roleMapper.insert(id,roleId[i]);
        }


    }

    @Override
    public void delRoleStatus(Integer roleId) {
        roleMapper.delRoleStatus(roleId);
    }

    @Override
    public void updateRoleStatus(Integer roleId) {
        roleMapper.updateRoleStatus(roleId);
    }

    /**
     * 修改角色权限 先删后增
     * @param rolePower
     */
    @Override
    public void updateRolePower(RolePower rolePower) {
        roleMapper.delRolePower(rolePower.getId());
        String[] ids = rolePower.getIds().split(",");
        for (int i = 0;i<ids.length;i++){
            Integer powerId = Integer.parseInt(ids[i]);
            roleMapper.insertRolePower(rolePower.getId(),powerId);
        }

    }

    @Override
    public void insertRole(String roleName) {


        roleMapper.insertRole(roleName);
    }
}
