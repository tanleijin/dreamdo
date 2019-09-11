package com.tlj.dreamdo.common.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tlj.dreamdo.common.factory.PageFactory;
import com.tlj.dreamdo.common.page.PageInfoBT;
import com.tlj.dreamdo.system.model.UserModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

public class BaseController {

    /**
     * 把service层的分页信息，封装为bootstrap table通用的分页封装
     */
    protected <T> PageInfoBT<T> packForBT(IPage<T> page) {
        return new PageInfoBT<T>(page);
    }

    protected <T> IPage<T> defaultPage(Class<T> c){
        return new PageFactory<T>().defaultPage();
    }

}
