package com.design.service;


import com.design.entity.Book;

import java.util.List;

/**
 * Created by cvter on 2017/5/17.
 */
public interface BookService {

    boolean save(Book book, String bookType);

    boolean bookDel(String uid);

    boolean bookAdjustPrice(String uid, int price);

    void bookAdjustStock(String uid, int stock);

    List<Book> selectAll();
    /*Book selectByUid(String uid);

    List<Book> selectByPaginate(int m, int n);



    boolean update(Book book);

    List<Book> selectAll();


    */
}
