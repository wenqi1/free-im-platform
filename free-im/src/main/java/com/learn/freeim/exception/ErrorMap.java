package com.learn.freeim.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.resource.EncodedResourceResolver;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 错误集合
 */
public class ErrorMap {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorMap.class);
    private static final HashMap<String, String> ERROR_MAP = new HashMap<>();

    static{
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/error", Locale.getDefault());
            for (String key : resourceBundle.keySet()) {
                ERROR_MAP.put(key, resourceBundle.getString(key));
            }
        } catch (Exception e) {
            throw new CommonException("1000", e, LOGGER);
        }
    }

    public static String getMessage(String code){
        return ERROR_MAP.get(code);
    }
}
