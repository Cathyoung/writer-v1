package com.slingerxv.writer.dao.model

import groovy.transform.CompileStatic

/**
 * 杂志信息
 * Created by chenyong on 2018/9/1
 */
@CompileStatic
class Magazine {
    Long id

    /**
     * 杂志名
     */
    String title

    Byte enable

    /**
     * 内容
     */
    String content

    String creator

    String updator

    Long createTime

    Long updateTime
}
