package com.xiaobubuya.shiroJwt.controller;

import com.xiaobubuya.shiroJwt.model.MyResultMap;
import org.apache.shiro.ShiroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 *
 * @Author 陶芃宇
 * @Description 异常处理
 * @Date 2020-11-17
 * @Time 17:09
 */
@RestControllerAdvice
public class ExceptionController {
    @Autowired
    private MyResultMap myResultMap;

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public MyResultMap handle401() {
        return myResultMap.fail().code(401).message("您没有权限访问！");
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public MyResultMap globalException(HttpServletRequest request, Throwable ex) {
        return myResultMap.fail()
                .code(getStatus(request).value())
                .message("访问出错，无法访问: " + ex.getMessage());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
