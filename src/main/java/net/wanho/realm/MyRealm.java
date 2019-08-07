package net.wanho.realm;

import net.wanho.mapper.RoleMapper;
import net.wanho.mapper.UserMapper;
import net.wanho.po.Role;
import net.wanho.po.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/7/30.
 */
public class MyRealm extends AuthorizingRealm{
    private Integer userId;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    //鉴权 分配权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //给当前对象赋予角色和权限
        System.out.println("鉴权");
        List<String> roles = new ArrayList<String>();
        List<Role> Allroles = roleMapper.selectRoleById(userId);
        //获取用户角色
        for (int i = 0;i<Allroles.size();i++){
            roles.add(Allroles.get(i).getRoleName());
        }
        //List<String> roles = Arrays.asList("admin","manager");
        // List<String> permissions = Arrays.asList("student:add","student:delete");
        List<String> permissions = Arrays.asList("student:select");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(permissions);


        return simpleAuthorizationInfo;
    }

    //认证 登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        //类型转换 ，char[]->string
//        String password = new String((char[])token.getCredentials());
        System.out.println("登录认证");
        User user = userMapper.getUserByName(username);
        userId = user.getId();
        if(user==null||"禁用".equals(user.getStatus())){
            throw new UnknownAccountException("账号有误或禁用");
        }
        return new SimpleAuthenticationInfo(username,user.getPassword(), ByteSource.Util.bytes(user.getSalt()),getName());
    }

}
