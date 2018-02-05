package com.design.dao;


import com.design.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao {

    int insert(Role record);

    int deleteByPrimaryKey(String uid);

    int updateByPrimaryKey(Role record);

    Role selectByPrimaryKey(String uid);

    Role selectByDescription(String description);

}