package com.tlj.dreamdo.common.factory;




import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tlj.dreamdo.common.util.http.HttpKit;

import javax.servlet.http.HttpServletRequest;

/**
*  BootStrap Table默认的分页参数创建
* @author tanleijin
* @date 2019/9/11 15:37
*/
public class PageFactory<T> {

    public IPage<T> defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
        int limit = Integer.valueOf(request.getParameter("limit"));     //每页多少条数据
        int offset = Integer.valueOf(request.getParameter("offset"));   //每页的偏移量(本页当前有多少条)
        //String sort = request.getParameter("sort");         //排序字段名称
        //String order = request.getParameter("order");       //asc或desc(升序或降序)
        IPage<T> page = new Page<>((offset / limit + 1), limit);
        return page;
    }
}
