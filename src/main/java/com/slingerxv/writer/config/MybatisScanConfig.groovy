package com.slingerxv.writer.config

import groovy.transform.CompileStatic
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Configuration

@CompileStatic
@Configuration
@MapperScan(basePackages = ['com.slingerxv.writer.dao.mapper', 'com.slingerxv.writer.security.mapper'], sqlSessionFactoryRef = 'sqlSessionFactory')
class MybatisScanConfig {
}

