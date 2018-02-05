package com.design.vo;

/**
 * Created by cvter on 2017/5/25.
 */
public class BookInShopCar {
    private String uid;

    private String name;

    private String author;

    private Integer price;

    private int nums;

    public BookInShopCar() {
        super();
    }

    public BookInShopCar(String uid, String name, String author, Integer price, int nums) {
        this.uid = uid;
        this.name = name;
        this.author = author;
        this.price = price;
        this.nums = nums;
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

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }
}
