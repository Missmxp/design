package com.design.dao;

import com.design.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by MaPei on 2018/1/18.
 */
@Repository
public interface UserDao {
    int insert(User user);
    String getName(int id);
    User selectByName(String name);
    User selectByPrimaryKey(String uid);
}
