package com.design.entity;

import java.util.Date;

/**
 * Created by cvter on 2017/5/31.
 */
public class SaleSum {

    String book_uid;
    int total_price;
    int nums;
    Date updateTime;
    boolean deleted;
    public SaleSum(String book_uid, int total_price, int nums, Date updateTime) {
        this.book_uid = book_uid;
        this.total_price = total_price;
        this.nums = nums;
        this.deleted=false;
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBook_uid() {
        return book_uid;
    }

    public void setBook_uid(String book_uid) {
        this.book_uid = book_uid;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public SaleSum(String book_uid, int total_price, int nums) {
        this.book_uid = book_uid;
        this.total_price = total_price;
        this.nums = nums;
    }

    public SaleSum() {
    }
}
