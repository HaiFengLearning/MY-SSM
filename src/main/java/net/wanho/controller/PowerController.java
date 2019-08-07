package net.wanho.controller;

import net.wanho.po.Power;
import net.wanho.service.PowerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2019/8/5.
 */
@Controller
public class PowerController {

        @Autowired
        private PowerServiceI powerServiceI;

    @RequestMapping("toPower")
    public String toPower(){
        return "power";
    }

    /**
     * 所有权限  三级菜单显示
     * @param parentId
     * @return
     */
    @RequestMapping("selectPowerOne")
    @ResponseBody
    public  List<Power> selectPowerOne(Integer parentId){
        System.out.println(parentId);
        List<Power> powers = powerServiceI.selectPowerOne(parentId);
        System.out.println(powers);
        return powers;
    }

    /**
     * 权限修改回显
     * @param powerId
     * @return
     */
    @RequestMapping("updatePowerInit")
    @ResponseBody
    public  Power updatePowerInit(Integer powerId){
        Power power = powerServiceI.selectPowerByPowerId(powerId);
        return power;
    }


    /**
     * 修改保存
     * @param power
     * @return
     */
    @RequestMapping("updatePowerName")
    @ResponseBody
    public String updatePowerName(Power power){
        powerServiceI.updatePower(power.getPowerId(),power.getPowerName());
        return "ok";
    }

    /**
     * 递归删除
     * @param powerId
     * @return
     */
    @RequestMapping("delPower")
    @ResponseBody
    public String delPower(Integer powerId){
        System.out.println(powerId);
        powerServiceI.delPower(powerId);
        return "ok";
    }

    /**
     * 新增下拉框数据  初始化
     * @return
     */
    @RequestMapping("insertPowerInit")
    @ResponseBody
    public List<Power> insertPowerInit(){
        List<Power> list = powerServiceI.selectAllPower();
        return list;
    }


    @RequestMapping("insertPower")
    @ResponseBody
    public String insertPower(Power power){
        System.out.println(power);
        powerServiceI.insertPower(power);
        return "ok";
    }


}
