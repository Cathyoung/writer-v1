package com.slingerxv.writer.config.log.annotation

import org.springframework.core.annotation.AliasFor

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Documented
@Target([ ElementType.METHOD, ElementType.TYPE ])
@Retention(RetentionPolicy.RUNTIME)
@interface RequestBodyLogger {
    @AliasFor("exclude")
    boolean value() default false;

    @AliasFor("value")
    boolean exclude() default false;
}