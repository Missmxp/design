package com.design.dao;

import com.design.entity.SaleSum;
import com.design.entity.UserBook;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface UserBookDao {
    UserBook selectByUuidAndBuid(@Param(value = "userUid") String userUid,
                                 @Param(value = "bookUid") String bookUid,
                                 @Param(value = "buyWay") boolean buyWay);

    int deleteByPrimaryKey(Integer id);

    List<SaleSum> saleSum(@Param(value = "today") Date today);

    //插入购买明细
    int insert(UserBook record);

    int insertSelective(UserBook record);

    UserBook selectByPrimaryKey(String userUid);

    List<UserBook> selectByUserUid(String userUid);

    UserBook selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(UserBook record);
}