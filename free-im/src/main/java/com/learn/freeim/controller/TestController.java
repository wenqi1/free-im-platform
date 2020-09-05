package com.learn.freeim.controller;

import com.learn.freeim.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping
    public void test() {
        throw new CommonException("1000", new RuntimeException(), LOGGER);
    }
}
