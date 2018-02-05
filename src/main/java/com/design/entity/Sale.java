package com.design.entity;

import java.util.Date;

public class Sale {
    private Integer id;

    private String bookUid;

    private Integer totalPrice;

    private Integer nums;

    private Boolean deleted;

    public Sale() {
        super();
    }

    public Sale(String bookUid, Integer totalPrice, Integer nums, Boolean deleted, Date createTime, Date updateTime) {
        this.bookUid = bookUid;
        this.totalPrice = totalPrice;
        this.nums = nums;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookUid() {
        return bookUid;
    }

    public void setBookUid(String bookUid) {
        this.bookUid = bookUid == null ? null : bookUid.trim();
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
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