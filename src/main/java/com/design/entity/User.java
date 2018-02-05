package com.design.entity;

import java.util.Date;

/**
 * Created by MaPei on 2018/1/18.
 */
public class User {
    private int id;                   //对应表中id字段
    private String uid;               //对应表中UID字段
    private String name;
    private String password;
    private Boolean deleted;
    private Date createTime;          //对应表中create_time字段
    private Date updateTime;          //对应表中update_time字段


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User() {

    }

    public User(String uid, String name, String password, Boolean deleted, Date createTime, Date updateTime) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.deleted = deleted;

        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
