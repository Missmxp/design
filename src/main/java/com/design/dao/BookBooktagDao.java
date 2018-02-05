package com.design.dao;


import com.design.entity.BookBooktag;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBooktagDao {

    int deleteByPrimaryKey(Integer id);

    int insert(BookBooktag record);

    BookBooktag selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(BookBooktag record);
}