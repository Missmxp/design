package com.design.service;


import com.design.entity.Sale;
import com.design.entity.SaleSum;

import java.util.List;

/**
 * Created by cvter on 2017/5/30.
 */
public interface SaleService {
    void save(List<SaleSum> listSale);

    int deleteByUid(String uid);

    int update(Sale sale);

    Sale selectByUid(String uid);

    List<SaleSum> saleTable();

    List<Sale> selectAll();
}
