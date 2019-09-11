package com.tlj.dreamdo.system.controller;

import com.tlj.dreamdo.core.log.SysLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
* 跳转策略
* @author tanleijin
* @date 2019/9/6 15:56
*/
@Controller
public class IndexController {

    
    //@SysLog("登陆页")
    @GetMapping({"/login" , "/user/login"})
    public String login() {
        return "user/login";
    }
    @GetMapping({"/admin" , "/admin/login"})
    public String adminLogin() {
        return "admin/login";
    }
}
