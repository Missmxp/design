package com.design.controller;

import com.design.authorization.manager.TokenManager;
import com.design.authorization.model.TokenModel;
import com.design.authorization.util.Constants;
import com.design.entity.*;
import com.design.exception.BusinessException;
import com.design.service.BookService;
import com.design.service.SaleService;
import com.design.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/admin/v1")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private SaleService saleService;

    /**
     * 管理员登录
     */
    @PostMapping(value = "/login")
    public Msg login(HttpServletResponse response,
                     @RequestParam String username,
                     @RequestParam String password) {
        if (userService.checkAdminLogin(username, password)) {
            User user = userService.selectByName(username);
            // 生成一个 token，保存用户登录状态
            TokenModel model = tokenManager.createToken(user.getUid());
            response.setHeader(Constants.AUTHORIZATION, model.toString());
            return Msg.success().setMessage("您已登录成功");
        }
        throw new BusinessException(50000, "用户名或密码错误");
    }

    /**
     * 管理员退出登录
     */
    @DeleteMapping(value = "/login")
    public Msg logoff(String userId) {
        tokenManager.deleteToken(userId);
        return Msg.success().setMessage("成功退出");
    }

    /**
     * 管理员图书上架
     */
    @PostMapping(value = "/book/add")
    public Msg bookAdd(Book book, String bookType) {
        if (bookService.save(book, bookType)) {
            return Msg.success().setMessage("图书上架成功");
        }
        return Msg.success().setMessage("图书上架失败");
    }

    /**
     * 管理员图书下架
     */
    @DeleteMapping(value = "/book/del/{uids}")
    public Msg bookDel(@PathVariable String uids) {
        if (bookService.bookDel(uids)) {
            return Msg.success().setMessage("图书删除成功");
        }
        return Msg.success().setMessage("图书删除失败");
    }

    /**
     * 图书价格和库存调整
     */
    @PutMapping(value = "/book/adjust")
    public Msg bookAdjust(Book book) {
        bookService.bookAdjustPrice(book.getUid(), book.getPrice());
        bookService.bookAdjustStock(book.getUid(), book.getStock());

        return Msg.success().setMessage("图书信息更新成功");
    }

    /**
     * 查看日销售统计表
     */
    @PostMapping(value = "/book/sale")
    public Msg bookSale(@RequestParam(defaultValue = "1") Integer pn,
                        @RequestParam(defaultValue = "7") Integer pageSize,
                        @RequestParam(defaultValue = "5") Integer navigatePages) {
        PageHelper.startPage(pn, pageSize);
        List<SaleSum> saleSums = saleService.saleTable();
        saleService.save(saleSums);
        PageInfo page = new PageInfo(saleSums, navigatePages);
        return Msg.success().add("page", page);
    }

    /**
     * 查看总销售表
     */
    @PostMapping(value = "/book/saleAll")
    public Msg bookSaleAll(@RequestParam(defaultValue = "1") Integer pn,
                           @RequestParam(defaultValue = "7") Integer pageSize,
                           @RequestParam(defaultValue = "5") Integer navigatePages) {
        PageHelper.startPage(pn, pageSize);
        List<Sale> allSale = saleService.selectAll();
        PageInfo page = new PageInfo(allSale, navigatePages);
        return Msg.success().add("page", page);
    }
}
