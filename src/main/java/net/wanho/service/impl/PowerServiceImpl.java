package net.wanho.service.impl;

import net.wanho.mapper.PowerMapper;
import net.wanho.po.Power;
import net.wanho.service.PowerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/2.
 */
@Service
public class PowerServiceImpl implements PowerServiceI{

    @Autowired
    private PowerMapper powerMapper;

    @Override
    public List<Power> selectAllPower() {
        return powerMapper.selectAllPower();
    }


    /**
     * zTree 回显
     * 初始化化权限树
     * 拼接treeNode属性
     */
    @Override
    public List<Map<String, Object>> selectPowerById(Integer id) {
        //查询所有权限
        List<Power> powers = powerMapper.selectAllPower();
        //查询指定角色的权限
        List<Power> powerById = powerMapper.selectPowerById(id);
        //包装zTree

        List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();

        Map<String, Object> map = null;
        for (int i=0; i<powers.size();i++){
            map = new HashMap<String, Object>();
            Power power = powers.get(i);
            map.put("powerId",power.getPowerId());
            map.put("parentId", power.getParentId());
            map.put("powerName", power.getPowerName());
            map.put("status",power.getStatus());

            //判断指定用户的角色是否在所有角色中包含，有则设置checked=true
            if(powerById != null && powerById.size()>0 && ListIsContainsObj(powerById,power)){
                map.put("checked",true);
            }else {
                map.put("checked",false);
            }
            list.add(map);


        }


        return list;
    }
  //权限三级菜单
    @Override
    public List<Power> selectPowerOne(Integer parentId) {
        return powerMapper.selectPowerOne(parentId);
    }

    //修改  查名称
    @Override
    public Power selectPowerByPowerId(Integer powerId) {
        return powerMapper.selectPowerByPowerId(powerId);
    }
 //修改保存
    @Override
    public void updatePower(Integer powerId, String powerName) {
        powerMapper.updatePower(powerId,powerName);
    }


    //递归删除
    @Override
    public void delPower(Integer powerId) {
        //list存储要删的对象
        List<Power> list = new ArrayList<Power>();
        //查询要删除的对象
        List<Power> list1 = requestPower(powerId, list);
        //删除power和role_power
        for (int i = 0;i<list1.size();i++){
            powerMapper.delPower(list1.get(i).getPowerId());
            powerMapper.delRolePower(list1.get(i).getPowerId());
        }



    }

    @Override
    public void insertPower(Power power) {
        powerMapper.insertPower(power);
    }

    //递归查询
    private List<Power> requestPower(Integer powerId,List<Power> list){
        //父权限
        Power power = powerMapper.selectPowerByPowerId(powerId);

        list.add(power);

        //子权限递归查询
        //得到父权限的子权限
        List<Power> list1 = powerMapper.selectPowerOne(power.getPowerId());
        if (list1.size() > 0) {
            //递归
            for (int i = 0; i < list1.size(); i++) {
                requestPower(list1.get(i).getPowerId(),list);
            }
        }
        return list;
    }


    //校验全部权限中是否有某个权限，有返回true
    private boolean ListIsContainsObj(List<Power> powers, Power power) {
        if(power == null || powers == null || powers.size()<=0){
            return false;
        }
        for (Power currPower : powers) {
            if(power.getPowerId().equals(currPower.getPowerId())){
                return true;
            }
        }
        return false;
    }
}
