package com.design.dao;


import com.design.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao {

    int insert(UserRole record);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(UserRole record);

    UserRole selectByUserUid(String userUid);
}