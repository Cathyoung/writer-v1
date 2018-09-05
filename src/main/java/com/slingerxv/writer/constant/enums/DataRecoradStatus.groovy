package com.slingerxv.writer.constant.enums

import groovy.transform.CompileStatic

/**
 * Created by chenyong on 2018/9/5
 */
@CompileStatic
enum DataRecoradStatus {
    ENABLE(1),//可用
    DISABLE(0),//不可用

    int value

    DataRecoradStatus(int value) {
        this.value = value
    }
}
