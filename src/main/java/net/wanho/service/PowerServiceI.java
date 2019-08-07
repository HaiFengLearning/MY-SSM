package net.wanho.service;

import net.wanho.po.Power;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/2.
 */
public interface PowerServiceI {

    List<Power> selectAllPower();
 //角色ztree的查询角色
    List<Map<String, Object>> selectPowerById(Integer id);

    List<Power> selectPowerOne(Integer parentId);

    Power selectPowerByPowerId(Integer powerId);
    void updatePower( Integer powerId, String powerName);


    void delPower(Integer powerId);


    void  insertPower(Power power);


}
