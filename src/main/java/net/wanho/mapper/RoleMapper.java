package net.wanho.mapper;

import net.wanho.po.Role;
import net.wanho.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */
public interface RoleMapper {

//所有角色 正常
  List<Role> selectAllRole(String status);

  //所有角色
  List<Role> selectAllRoleAndAllStatus();

  List<Role> selectRoleById(Integer id);
//修改角色
  void  delRole(Integer id);
  void  insert(@Param("id") Integer id,@Param("roleId") Integer roleId);

//逻辑删  改状态
  void delRoleStatus(Integer roleId);
  //启用
  void  updateRoleStatus(Integer roleId);

  //修改角色权限
  void delRolePower(Integer roleId);
  void  insertRolePower(@Param("roleId") Integer roleId,@Param("powerId") Integer powerId);

  //增加角色
  void insertRole(String roleName);


























}
