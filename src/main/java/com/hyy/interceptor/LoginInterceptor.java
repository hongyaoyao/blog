package com.hyy.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.interceptor
 * @CLASS_NAME: LoginInterceptor
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 16:47
 * @Emial: 1299694047@qq.com
 */

/**
 * 自定义的Interceptor拦截器类，用于拦截未登录用户访问后台管理页面
 * 注意：自定义Mvc的Interceptor拦截器类
 *  1、使用@Component注解声明
 *  2、自定义注册类将自定义的Interceptor拦截器类进行注册使用
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
