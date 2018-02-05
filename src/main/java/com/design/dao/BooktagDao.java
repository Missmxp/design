package com.design.dao;


import com.design.entity.Booktag;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BooktagDao {
    int deleteByPrimaryKey(String id);

    int insert(Booktag record);

    Booktag selectByPrimaryKey(String id);

    Booktag selectByDescription(String description);

    List<Booktag> selectAll();

    int updateByPrimaryKey(Booktag record);
}