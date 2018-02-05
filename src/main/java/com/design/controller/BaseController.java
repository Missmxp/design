package com.design.controller;

import com.design.entity.Msg;
import com.design.exception.BusinessException;
import com.design.exception.ExceptionCode;
import com.design.exception.ParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基类，封装异常处理
 */
public class BaseController {

    /**
     * 基于@ExceptionHandler异常处理
     */
    @ExceptionHandler
    @ResponseBody
    public Msg exp(Exception ex) {
        ex.printStackTrace();
        // 根据不同错误转向不同页面
        if (ex instanceof BusinessException) {
            return Msg.fail()
                    .setCode(((BusinessException) ex).getCode())
                    .setMessage(ex.getMessage());
        } else if (ex instanceof ParameterException) {
            return Msg.fail()
                    .setCode(ExceptionCode.EX_10001.getCode())
                    .setMessage(ex.getMessage());
        } else {
            return Msg.fail()
                    .setCode(ExceptionCode.EX_10002.getCode())
                    .setMessage(ExceptionCode.EX_10002.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/error/400")
    public Msg hand400Error() {
        return Msg.fail()
                .setCode(ExceptionCode.EX_400.getCode())
                .setMessage(ExceptionCode.EX_400.getMessage());
    }

    @ResponseBody
    @RequestMapping("/error/403")
    public Msg hand403Error() {
        return Msg.fail()
                .setCode(ExceptionCode.EX_403.getCode())
                .setMessage(ExceptionCode.EX_403.getMessage());
    }

    @ResponseBody
    @RequestMapping("/error/404")
    public Msg hand404Error() {
        return Msg.fail()
                .setCode(ExceptionCode.EX_404.getCode())
                .setMessage(ExceptionCode.EX_404.getMessage());
    }

    @ResponseBody
    @RequestMapping("/error/405")
    public Msg hand405Error() {
        return Msg.fail()
                .setCode(ExceptionCode.EX_405.getCode())
                .setMessage(ExceptionCode.EX_405.getMessage());
    }

    @ResponseBody
    @RequestMapping("/error/500")
    public Msg hand500Eerror() {
        return Msg.fail()
                .setCode(ExceptionCode.EX_500.getCode())
                .setMessage(ExceptionCode.EX_500.getMessage());
    }
}
