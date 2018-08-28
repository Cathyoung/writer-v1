package com.slingerxv.writer.controller

import com.slingerxv.writer.core.ResponseBean

import groovy.transform.CompileStatic
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by chenyong on 2018/8/26
 */

@Slf4j
@RestController
@RequestMapping("/test")
@CompileStatic
class TestController extends BaseController {
    @GetMapping("/list")
    ResponseBean list(@RequestParam(value = "createStartDate", required = false) String createStartDate) {
        return ResponseBean.success(createStartDate)
    }
}
