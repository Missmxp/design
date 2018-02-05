package com.design.service;


import com.design.entity.User;
import com.design.vo.BookInShopCar;

import java.util.List;

/**
 * Created by cvter on 2017/5/17.
 */
public interface UserService {

    Boolean buy(String userUid, String bookUid, int nums);

    Boolean deleteOneBook(String userUid, String bookUid);

    Boolean addShopCar(String userUid, String bookUid, int nums);

    Boolean clearShopCar(String userUid);

    Boolean updateShopCar(String userUid, String bookUid, int count);

    List<BookInShopCar> getShopCar(String userUid);

    Boolean checkLogin(String username, String password);

    User selectByName(String name);

    boolean checkAdminLogin(String username, String password);

    Boolean checkRegister(String username, String password);
/*






    int save(User record);



    User selectByUid(String uid);

    int update(User record);

    int deleteByUid(String uid);

    List<User> selectAll(int m, int n);*/
}
