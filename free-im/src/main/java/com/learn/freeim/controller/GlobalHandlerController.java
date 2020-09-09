package com.learn.freeim.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learn.freeim.entity.Result;
import com.learn.freeim.exception.CommonException;
import com.learn.freeim.exception.ErrorMap;

/**
 * 全球异常处理类
 */
@ControllerAdvice
@ResponseBody
public class GlobalHandlerController {

    @ExceptionHandler(CommonException.class)
    public Result<Map> HandlerException(CommonException e){
        String code = e.getCode();
        Logger logger = e.getLogger();
        logger.error(ErrorMap.getMessage(code), e);
        return Result.fail(code, ErrorMap.getMessage(code));
    }
}
