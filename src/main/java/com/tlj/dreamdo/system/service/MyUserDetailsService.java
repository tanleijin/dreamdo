package com.tlj.dreamdo.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tlj.dreamdo.system.model.AdminModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Component;


import java.util.Collection;

/**
* 用户登陆查询的操作
* @author tanleijin
* @date 2019/9/10 15:40
*/
@Component
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);
    
    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("表单登录用户名:" + username);
        AdminModel loginAdmin = adminService.getOne(new LambdaQueryWrapper<AdminModel>().eq(AdminModel::getUsername, username));
        if(loginAdmin == null) {
            throw new UsernameNotFoundException("找不到该账户信息！");//抛出异常，会根据配置跳到登录失败页面
    }
        
        LOGGER.info("username: {} , password :{}" , username , loginAdmin.getPassword());
        
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        return new User(username, loginAdmin.getPassword(), true,true,true,true,authorities);
    }

}
