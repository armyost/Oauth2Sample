package com.armyost.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class DemoPostController {
    
    private static final Logger logger = LoggerFactory.getLogger(DemoPostController.class);

    @GetMapping(value = "/ping")
    public String getPing(){
        String response_val = "pong";
        logger.info("!!! Ping has called !!!");
        return response_val;
    }

    @GetMapping(value = "/mainPage")
    public String listSample(){
        String response_val = "mainPage";
        logger.info("!!! /mainPage has called !!!");
        //Business Logic 추가
        return response_val;
    }

    @GetMapping(value = "/images/permitPath")
    public String addSample(){
        String response_val = "permitPage";
        logger.info("!!! /images/permitPath has called !!!");
        //Business Logic 추가
        return response_val;
    }

} 