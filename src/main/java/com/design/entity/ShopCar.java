package com.design.entity;

import java.util.Date;

public class ShopCar {
    private Integer id;

    public ShopCar() {
        super();
    }

    public ShopCar(String userUid, String bookUid, Integer nums, Boolean deleted, Date createTime, Date updateTime) {
        this.userUid=userUid;
        this.bookUid=bookUid;
        this.nums=nums;
        this.deleted=deleted;
        this.createTime=createTime;
        this.updateTime=updateTime;
    }

    private String userUid;

    private String bookUid;

    private Integer nums;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid == null ? null : userUid.trim();
    }

    public String getBookUid() {
        return bookUid;
    }

    public void setBookUid(String bookUid) {
        this.bookUid = bookUid == null ? null : bookUid.trim();
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
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
}