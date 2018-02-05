package com.design.dao;

import com.design.entity.Sale;
import com.design.entity.SaleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDao {
    int countByExample(SaleExample example);

    int deleteByExample(SaleExample example);

    int deleteByPrimaryKey(String uid);

    int insert(Sale record);

    int insertSelective(Sale record);

    List<Sale> selectAll();

    List<Sale> selectByExample(SaleExample example);

    int insertPart();

    Sale selectByBookUid(String bookUid);

    int updateByExampleSelective(@Param("record") Sale record, @Param("example") SaleExample example);

    int updateByExample(@Param("record") Sale record, @Param("example") SaleExample example);

    int updateByPrimaryKeySelective(Sale record);

    int updateByPrimaryKey(Sale record);
}