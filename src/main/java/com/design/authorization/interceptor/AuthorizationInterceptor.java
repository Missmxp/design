package com.design.authorization.interceptor;

import com.design.authorization.annotation.Authorization;
import com.design.authorization.manager.TokenManager;
import com.design.authorization.model.TokenModel;
import com.design.authorization.util.Constants;
import com.design.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 自定义拦截器，对请求进行身份验证
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 从 header 中得到 token
        String authentication = request.getHeader(Constants.AUTHORIZATION);

        TokenModel model = manager.getToken(authentication);
        // 验证 token
        if (manager.checkToken(model)) {
            // 如果 token 验证成功，将 token 对应的用户 id 存在 request 中，便于之后注入
            request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
            return true;
        }
        if (method.getAnnotation(Authorization.class) != null) {
            try {
                ResponseUtil.write(response, HttpServletResponse.SC_UNAUTHORIZED, Constants.RESPONSE_AUTH_MSG);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }
}
