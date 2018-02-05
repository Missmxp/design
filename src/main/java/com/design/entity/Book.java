package com.design.entity;

import com.design.utils.GetRedisKey;

import java.util.Date;

public class Book extends AbstractParent implements GetRedisKey {

    private String name;

    private String author;

    private Integer price;

    private Integer stock;

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public Book() {
    }

    public Book(String name, String author, Integer price, Integer stock, String description) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public Book(String uid, Date createTime, Date updateTime, Boolean deleted, String name, String author, Integer price, Integer stock, String description) {
        super(uid, createTime, updateTime, deleted);
        this.name = name;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    @Override
    public String getName() {
        return null;
    }
}