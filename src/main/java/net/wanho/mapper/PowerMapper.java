package net.wanho.mapper;

import net.wanho.po.Power;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/8/2.
 */
public interface PowerMapper {

    List<Power> selectAllPower();

//角色id 找权限
    List<Power> selectPowerById(@Param("roleId") Integer roleId);

    //一二三级菜单
    List<Power> selectPowerOne(@Param("parentId") Integer parentId);

    Power selectPowerByPowerId(Integer powerId);

    void updatePower(@Param("powerId") Integer powerId,@Param("powerName") String powerName);

    void delPower(Integer powerId);
    void delRolePower(Integer powerId);

    void  insertPower(Power power);






}
