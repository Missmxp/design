package com.design.dao;

import com.design.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by MaPei on 2018/1/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;
    @Test
    public void insertTest(){
        User user=new User();
        user.setName("mapei");
        user.setUid("11223");
        user.setPassword("111");
//        System.out.println(userDao.getName(1));
        userDao.insert(user);
    }
}