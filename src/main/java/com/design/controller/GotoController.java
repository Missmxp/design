package com.design.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 该Controller用于页面的跳转
 * <p>
 * Created by cvter on 2017/5/21.
 */
@Controller

public class GotoController extends BaseController {

    @RequestMapping("/index")
    public String index() {return "index";  }

    @RequestMapping(value = "/book/detail")
    public String detail() {
        return "detail";
    }

    @RequestMapping(value = "/book/panic")
    public String panic() {
        return "panic";
    }

    @RequestMapping(value = "/book/search")
    public String search() {
        return "result";
    }

    @RequestMapping(value = "/book/shopcar")
    public String shopCar() {
        return "shopcar";
    }

    @RequestMapping(value = "/admin")
    public String adminIndex() {
        return "admin/manager";
    }

    @RequestMapping(value = "/admin/login")
    public String adminLogin() {
        return "admin/login";
    }

    @RequestMapping(value = "admin/book/sale")
    public String sale() {
        return "admin/sale";
    }

    @RequestMapping(value = "admin/book/saleAll")
    public String saleAll() {
        return "admin/saleall";
    }
}
