package com.design.service.impl;


import com.design.dao.SaleDao;
import com.design.dao.UserBookDao;
import com.design.entity.Sale;
import com.design.entity.SaleSum;
import com.design.service.SaleService;
import com.design.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by cvter on 2017/5/30.
 */
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleDao saleDao;
    @Autowired
    private UserBookDao userBookDao;

    /**
     * 销售图书增加
     *
     * @param listSale  销售图书实体
     * @return
     */
    @Override
    public void save(List<SaleSum> listSale) {
        for(int i=0;i<listSale.size();++i){
            System.out.println("-----------------------------i:"+i);
            System.out.println("------------------------------bookuid:"+listSale.get(i).getBook_uid());
            String bookUid = listSale.get(i).getBook_uid();
            int totalPrice=listSale.get(i).getTotal_price();
            int nums=listSale.get(i).getNums();
            Sale sale=this.selectByUid(bookUid);
            //Sale sale=saleDao.selectByBookUid(bookUid);

            if(sale!=null){
                int newTotalPrice=sale.getTotalPrice()+totalPrice;
                int newNums=sale.getNums()+nums;
                Date time=listSale.get(i).getUpdateTime();

                System.out.println("----------time:"+time);
                if(!time.after(sale.getUpdateTime())){
                    continue;
                }
                sale.setTotalPrice(newTotalPrice);
                sale.setNums(newNums);
                sale.setUpdateTime(new Date());
                saleDao.updateByPrimaryKey(sale);
            }
            else{
                Sale newSale=new Sale(bookUid,totalPrice,nums,false,new Date(),new Date());
                saleDao.insert(newSale);
            }
        }
    }

    /**
     * 销售图书删除
     *
     * @param uid  销售图书UID
     * @return
     */
    @Override
    public int deleteByUid(String uid) {
        return saleDao.deleteByPrimaryKey(uid);
    }

    /**
     * 销售图书更新
     *
     * @param sale   销售图书实体
     * @return
     */
    @Override
    public int update(Sale sale) {
        return saleDao.updateByPrimaryKey(sale);
    }

    /**
     * 销售图书查询
     *
     * @param bookUid  图书UID
     * @return 销售图书实体
     */
    @Override
    public Sale selectByUid(String bookUid) {
        return saleDao.selectByBookUid(bookUid);
    }

    /**
     * 销售统计
     *
     * @return  销售统计列表
     */
    @Override
    public List<SaleSum> saleTable() {
        Date date=new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String str = format.format(date);
        String strDate=str+" 00:00:00";
        Date today= TimeUtil.strToDateLong(strDate);
        List<SaleSum> list=userBookDao.saleSum(today);
        return list;
    }

    /**
     * 查询销售列表
     *
     * @return  销售列表
     */
    @Override
    public List<Sale> selectAll() {
        return saleDao.selectAll();
    }
}
