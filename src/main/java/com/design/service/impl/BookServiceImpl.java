package com.design.service.impl;
import com.design.dao.BookBooktagDao;
import com.design.dao.BookDao;
import com.design.dao.BooktagDao;
import com.design.entity.Book;
import com.design.entity.BookBooktag;
import com.design.entity.Booktag;
import com.design.exception.ParameterException;
import com.design.service.BookService;

import com.design.utils.UIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by cvter on 2017/5/17.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private BooktagDao booktagDao;
    @Autowired
    private BookBooktagDao bookBooktagDao;

    /**
     *增加图书
     *
     * @param book  图书
     * @param bookType  图书类型
     * @return 成功或失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Book book, String bookType) {
        if (StringUtils.isAnyBlank(book.getName(), book.getAuthor(), book.getPrice() + "", book.getStock() + "", book.getDescription(),bookType)) {
            throw new ParameterException("参数为空");
        }
        book.setUid(UIDUtil.getRandomUID());
        Date date=new Date();
        //Booktag booktag=booktagDao.selectByDescription(bookType);
        //BookBooktag bookBooktag=new BookBooktag(book.getUid(),booktag.getUid(),false,date,date);
        //bookBooktagDao.insert(bookBooktag);
        return bookDao.insert(book);
    }

    /**
     *删除图书
     *
     * @param uid  图书UID
     * @return  成功或失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bookDel(String uid) {
        if (StringUtils.isBlank(uid)) {
            throw new ParameterException("参数为空");
        }
//        Book book = bookService.selectByUid(uid);
        //Book book = this.selectByUid(uid);
        Book book=bookDao.selectByBookUid(uid);
        book.setDeleted(true);
        //book.setUpdateTime(new Date());
        //return this.update(book);
        return bookDao.updateByPrimaryKey(book);
    }

    /**
     * 修改图书价格
     *
     * @param uid  图书UID
     * @param price  要修改的价钱
     * @return  成功或失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bookAdjustPrice(String uid, int price) {
        if (StringUtils.isAnyBlank(uid, price + "")) {
            throw new ParameterException("参数为空");
        }
        Book book=bookDao.selectByBookUid(uid);
        book.setPrice(price);
        book.setUpdateTime(new Date());
        return bookDao.updateByPrimaryKey(book);
    }

    /**
     * 修改图书库存
     *
     * @param uid  图书UID
     * @param stock  要修改的库存数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bookAdjustStock(String uid, int stock) {
        if (StringUtils.isAnyBlank(uid, stock + "")) {
            throw new ParameterException("参数为空");
        }
        Book book=bookDao.selectByBookUid(uid);
        book.setStock(stock);
        book.setUpdateTime(new Date());
        bookDao.updateByPrimaryKey(book);
    }

    /**
     * 查看所有图书
     *
     * @return  图书列表
     */
    @Override
    public List<Book> selectAll() {
        return bookDao.selectAll();
    }


    /***//**
     * 查看所有图书
     *
     * @return  图书列表
     *//*
    @Override
    public List<Book> selectAll() {
        return bookDao.selectAll();
    }

    *//**
     * 图书信息更新
     *
     * @param book  要更新的图书
     * @return  成功或失败
     *//*
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Book book) {
        return bookDao.updateByPrimaryKey(book);
    }
    *//**
     * 查询图书
     *
     * @param uid   图书UID
     * @return  图书实体
     *//*
    @Override
    public Book selectByUid(String uid) {
        return bookDao.selectByBookUid(uid);
    }

    *//**
     *查询所有图书，分页查询
     *
     * @param m
     * @param n
     * @return   图书列表
     *//*
    @Override
    public List<Book> selectByPaginate(int m, int n) {
        return bookDao.selectByPaginate(m, n);
    }*/
}
