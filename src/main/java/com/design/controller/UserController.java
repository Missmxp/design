package com.design.controller;

import com.design.authorization.manager.TokenManager;
import com.design.authorization.model.TokenModel;
import com.design.authorization.util.Constants;
import com.design.entity.Msg;
import com.design.entity.User;
import com.design.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户操作类
 */
@RestController
@RequestMapping("/user/v1")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;

    /**
     * 用户登录
     */
    @PostMapping(value = "/login")
    public Msg login(HttpServletResponse response, String username, String password) {
        System.out.println("login");
        boolean flag = userService.checkLogin(username, password);
        if (flag) {
            User user = userService.selectByName(username);
            // 生成一个 token，保存用户登录状态
            TokenModel model = tokenManager.createToken(user.getUid());
            response.setHeader(Constants.AUTHORIZATION, model.toString());
            response.setHeader("username", user.getName());
            return Msg.success().setMessage("请去首页进行选购").add("userinfo", user);
        }
        return Msg.fail().setMessage("用户名或者密码错误");
    }

    /**
     * 用户注册
     */
    @PutMapping(value = "/login")
    public Msg register(HttpServletResponse response, String username, String password) {
        boolean flag = userService.checkRegister(username, password);
        if (flag) {
            User user = userService.selectByName(username);
            // 生成一个 token，保存用户登录状态
            TokenModel model = tokenManager.createToken(user.getUid());
            response.setHeader(Constants.AUTHORIZATION, model.toString());
            response.setHeader("username", user.getName());
            return Msg.success().setMessage("注册成功").add("userinfo", user);
        }
        return Msg.fail().setMessage("用户名已存在");
    }


    /**
     * 用户退出登录
     */
    @ResponseBody
    @DeleteMapping(value="/login")
    public Msg loginOff(String userID) {
        tokenManager.deleteToken(userID);
        return Msg.success().setMessage("注销成功");

    }
}
