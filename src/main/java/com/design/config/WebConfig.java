package com.design.config;

import com.design.authorization.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by MaPei on 2018/1/22.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public AuthorizationInterceptor authorizationInterceptor(){return new AuthorizationInterceptor();}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor()).addPathPatterns("/*/**").
                excludePathPatterns("/");
        super.addInterceptors(registry);
    }
}
