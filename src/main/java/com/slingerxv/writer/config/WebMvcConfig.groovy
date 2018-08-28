package com.slingerxv.writer.config

import com.slingerxv.writer.config.log.LogInterceptor
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
@CompileStatic
class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    LogInterceptor logInterceptor

    @Override
    void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor).addPathPatterns("/**")
    }

}
