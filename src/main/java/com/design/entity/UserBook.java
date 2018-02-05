package com.design.entity;

import java.util.Date;

public class UserBook {
    private Integer id;

    private String userUid;

    private String bookUid;

    private Integer buyPrice;

    private Integer buyNums;

    private Boolean buyWay;

    private Boolean deleted;

    private Date createTime;

    public UserBook() {
        super();
    }

    public UserBook(String userUid, String bookUid, Integer buyPrice, Integer buyNums, Boolean buyWay, Boolean deleted, Date createTime, Date updateTime) {
        this.userUid = userUid;
        this.bookUid = bookUid;
        this.buyPrice = buyPrice;
        this.buyNums = buyNums;
        this.buyWay = buyWay;
        this.deleted = deleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

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

    public Integer getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Integer buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getBuyNums() {
        return buyNums;
    }

    public void setBuyNums(Integer buyNums) {
        this.buyNums = buyNums;
    }

    public Boolean getBuyWay() {
        return buyWay;
    }

    public void setBuyWay(Boolean buyWay) {
        this.buyWay = buyWay;
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