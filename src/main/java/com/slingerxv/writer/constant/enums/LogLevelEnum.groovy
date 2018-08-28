package com.slingerxv.writer.constant.enums

import groovy.transform.CompileStatic

/**
 * Created by chenyong on 2018/8/26
 */
@CompileStatic
enum LogLevelEnum {
    /**
     * 警告系统能继续运行, 但是必须引起关注
     */
    WARN,
    /**
     * 系统发生了严重的错误
     */
    ERROR,
    /**
     * 重要的业务逻辑处理完成日志输出
     */
    INFO,
    /**
     * 调试
     */
    DEBUG
}