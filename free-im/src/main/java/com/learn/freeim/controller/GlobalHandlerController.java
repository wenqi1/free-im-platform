package com.learn.freeim.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learn.freeim.entity.Result;
import com.learn.freeim.exception.CommonException;
import com.learn.freeim.exception.ErrorMap;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

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

    @ExceptionHandler(BindException.class)
    public Result<Map> handleBindException(BindException exception) {
        Map<String, String> errorInfo = new HashMap<String, String>();
        BindingResult bindingResult = exception.getBindingResult();
        for(int i = 0; i < bindingResult.getFieldErrors().size(); i++){
            FieldError fieldError = bindingResult.getFieldErrors().get(i);
            errorInfo.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return Result.fail("1001", ErrorMap.getMessage("1001"), errorInfo);
    }

}
