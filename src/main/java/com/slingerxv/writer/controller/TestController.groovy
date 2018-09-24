package com.slingerxv.writer.controller;

import com.alibaba.fastjson.JSON;
import groovy.util.logging.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenyong on 2018/9/10
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @RequestMapping("/yes")
    @ResponseBody
    public String test(){
        log.error("下载文件异常,requestDto=")
        return "yes";
    }
}
