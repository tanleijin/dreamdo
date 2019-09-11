package com.tlj.dreamdo.common.page;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
* 分页结果的封装(for Bootstrap Table)
* @author tanleijin
* @date 2019/9/11 15:46
*/
public class PageInfoBT<T> {

    // 结果集
    private List<T> rows;

    // 总数
    private long total;

    public PageInfoBT(IPage<T> page) {
        this.rows = page.getRecords();
        this.total = page.getTotal();
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
