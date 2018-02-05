package com.design.controller;

import com.design.entity.Book;
import com.design.entity.Msg;
import com.design.exception.BusinessException;
import com.design.exception.ExceptionCode;

import com.design.service.BookService;
import com.design.service.UserService;
import com.design.vo.BookInShopCar;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


/**
 * 图书Controller
 */
@RestController
@RequestMapping("/book/v1")
public class BookController extends BaseController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

/*


    @Autowired
    private RedisCountHotBookUtil redisCountHotBookUtil;

    @Autowired
    private RedisTopTenUtil redisTopTenUtil;

    @Autowired
    private PanicService panicService;

*/

    @GetMapping(value = "/hello")
    public Msg hello(){
        return Msg.success().setMessage("hello");
    }


    /**
     * 购买图书
     */
    @PostMapping(value = "/buy")
    public Msg buy(@RequestParam String userId, @RequestParam String bookuid, @RequestParam int nums) {
        boolean flag = userService.buy(userId, bookuid, nums);
        if (flag) {
            return Msg.success().setMessage("购买成功");
        }
        return Msg.fail().setMessage("库存不足");
    }

    /**
     * 添加购物车
     */
    @PostMapping(value = "/shop/car")
    public Msg shopCarPost(@RequestParam String userId, @RequestParam String bookuid, @RequestParam String nums) {
        String pt = "^[0-9]+$";
        boolean isNum = nums.matches(pt);
        if (!isNum) {
            throw new BusinessException(ExceptionCode.EX_20010.getCode(), ExceptionCode.EX_20010.getMessage());
        }
        int num = Integer.parseInt(nums);
        if (num <= 0) {
            throw new BusinessException(ExceptionCode.EX_200003.getCode(), ExceptionCode.EX_200003.getMessage())    ;
        }
        boolean flag = userService.addShopCar(userId, bookuid, num);
        if (flag) {
            return Msg.success().setMessage("添加成功，尽快购买");
        }
        return Msg.fail().setMessage("添加失败");
    }

    /**
     * 清空购物车
     */
    @DeleteMapping(value = "/shop/car")
    public Msg shopCarDelete(@RequestParam String userId, @RequestParam String bookUid) {
        System.out.println(bookUid);
        /**
         *boouid为空就执行清空购物车
         * 反之，删除指定图书
         */
        if (!StringUtils.isEmpty(bookUid) && !"0".equals(bookUid)) {
            boolean flag = userService.deleteOneBook(userId, bookUid);
            if (flag) {
                return Msg.success().setMessage("删除成功");
            }
            return Msg.fail().setMessage("删除失败");
        }
        boolean flag = userService.clearShopCar(userId);
        if (flag) {
            return Msg.success().setMessage("清空完毕");
        }
        return Msg.fail().setMessage("清空失败");
    }


    /**
     * 更新购物车
     */
    @PutMapping(value = "/shop/car")
    public Msg shopCarPut(@RequestParam String userId, @RequestParam String bookuid, @RequestParam int flag) {
        boolean isTrue = userService.updateShopCar(userId, bookuid, flag);
        if (isTrue) {
            return Msg.success().setMessage("修改成功");
        }
        return Msg.fail().setMessage("修改成功");
    }


    /**
     * 获取购物车详情
     */

    @GetMapping(value = "/details")
    public Msg shopCarSizeGet(@RequestParam String userId) {
        List<BookInShopCar> bookList = userService.getShopCar(userId);
        if (bookList == null) {
            return Msg.success().add("size", 0);
        }
        return Msg.success().add("size", bookList.size());
    }


    /**
     * 获取图书列表
     */
    @PostMapping("/list")
    public Msg list(@RequestParam(defaultValue = "1") Integer pn,
                    @RequestParam(defaultValue = "7") Integer pageSize,
                    @RequestParam(defaultValue = "5") Integer navigatePages) {
        System.out.println("list");

        PageHelper.startPage(pn, pageSize);
        List<Book> allBook = bookService.selectAll();
        PageInfo page = new PageInfo(allBook, navigatePages);
        return Msg.success().add("page", page);
    }



    /**
     * 获取图书详情
     *
     * @param uid
     * @return
     *//*

    @ResponseBody
    @RequestMapping(value = "/detail/{uid}", method = RequestMethod.GET)
    public Msg list(@PathVariable String uid) {

        Book book = (Book) redisCountHotBookUtil.getInRedis(uid, Book.class);//在Redis中查询，未查询到，在去Mysql中查找

        redisTopTenUtil.putRedisTopTen(uid);//将图书id存到redis，统计热点图书。
        if (book == null) {
            Book bk = bookService.selectByUid(uid);
            redisCountHotBookUtil.putRedis(bk, Book.class);
            book = bk;
        }
        return Msg.success().add("book", book);
    }
*/




/*

    *//**
     * 获取书籍分类列表
     *
     * @return 响应实体 {@link Msg}
     *//*
    @ResponseBody
    @RequestMapping("/booktag")
    public Msg listBookTags() {
        List<Booktag> booktags=booktagService.selectAll();

        return Msg.success().add("booktags", booktags);
    }

    *//**
     * 点击量前十图书(不够十本，全部返回)
     *
     * @return
     *//*
    @ResponseBody
    @RequestMapping(value="/hotBook")
    public Msg getTopTen() {
        Set<String> topTen=redisTopTenUtil.getInRedisTopTen();
        List<Book> topTenBook=new ArrayList(10);
        for (String bookUid : topTen) {
            Book book=(Book) redisCountHotBookUtil.getInRedis(bookUid, Book.class);//在Redis中查询，未查询到，在去Mysql中查找
            if (book == null) {
                Book bk=bookService.selectByUid(bookUid);
                redisCountHotBookUtil.putRedis(bk, Book.class);
                book=bk;
            }
            topTenBook.add(book);
        }
        Collections.reverse(topTenBook);
        return Msg.success().add("TOP_TEN", topTenBook);
    }











    *//**
     * 获取抢购图书列表
     *
     * @param pn            页码
     * @param pageSize      页大小
     * @param navigatePages 页数
     * @return
     *//*
    @ResponseBody
    @RequestMapping(value="/panic/list", method= RequestMethod.POST)
    public Msg panicList(@RequestParam(defaultValue="1") Integer pn,
                         @RequestParam(defaultValue="7") Integer pageSize,
                         @RequestParam(defaultValue="5") Integer navigatePages) {
        PageHelper.startPage(pn, pageSize);
        List<Panic> allPBook=panicService.selectAll();
        PageInfo page=new PageInfo(allPBook, navigatePages);
        return Msg.success().add("page", page);
    }

    *//**
     * 获取抢购图书详情
     *
     * @param uid 图书UID
     * @return
     *//*
    @RequestLimit(value=3, msg="三秒防刷")
    @ResponseBody
    @RequestMapping(value="/panic/detail/{uid}", method= RequestMethod.GET)
    public Msg panicDetil(@PathVariable String uid) {
        Panic panic=panicService.selectByUID(uid);
        return Msg.success().add("panic", panic);
    }

    *//**
     * 图书抢购
     *
     * @param bookUid
     * @param user
     * @return
     *//*
//    @RequestLimit(value = 3, msg = "三秒防刷")
    @Authorization
    @ResponseBody
    @RequestMapping(value="/panic", method= RequestMethod.POST)
    public Msg bookPanic(@RequestParam String bookUid,
                         @CurrentUser User user) {
        if (panicService.executePanic(bookUid, user.getUid())) {
            return Msg.success().setMessage("抢购成功");
        }
        return Msg.success().setMessage("抢购失败");
    }*/
}
