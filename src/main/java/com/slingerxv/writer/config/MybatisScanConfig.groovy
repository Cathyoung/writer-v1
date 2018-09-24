package com.slingerxv.writer.config

import groovy.transform.CompileStatic
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tk.mybatis.spring.mapper.MapperScannerConfigurer

@CompileStatic
@Configuration
class MybatisScanConfig {
    private static final String BASE_PACKAGE = "com.slingerxv.writer.dao.mapper,com.slingerxv.writer.security.mapper";
    private static final String SQL_SESSION_FACTORY_BEAN_NAME = "sqlSessionFactory";

    /**
     * 重新注入通用mapper的数据资源
     *
     * @return MapperScannerConfigurer
     */
    @Bean
    public MapperScannerConfigurer getMapper() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage(BASE_PACKAGE);
        configurer.setSqlSessionFactoryBeanName(SQL_SESSION_FACTORY_BEAN_NAME);
        return configurer;
    }
}

