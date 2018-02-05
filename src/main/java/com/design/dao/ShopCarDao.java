package com.design.dao;

import com.design.entity.ShopCar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopCarDao {
    ShopCar selectByUuidAndBuid(@Param(value = "userUid") String userUid,
                                @Param(value = "bookUid") String bookUid,
                                @Param(value = "deleted") boolean deleted);

    List<ShopCar> selectByUseUid(@Param(value = "userUid") String userUid,
                                 @Param(value = "deleted") boolean deleted);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopCar record);

    int updateByPrimaryKey(ShopCar record);
}