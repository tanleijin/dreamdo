package com.tlj.dreamdo.system.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
*
* @author tanleijin
* @date 2019/9/6 17:09
*/
@TableName("tbl_admin")
public class AdminModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 昵称
     */
    private String nackname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getNackname() {
        return nackname;
    }

    public void setNackname(String nackname) {
        this.nackname = nackname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminModel{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", nackname=" + nackname +
        ", username=" + username +
        ", password=" + password +
        "}";
    }
}
