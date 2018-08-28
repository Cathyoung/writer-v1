package com.slingerxv.writer.core

import com.slingerxv.writer.constant.enums.ResponseCodeEnum

/**
 * Created by chenyong on 2018/8/26
 */
class ResponseBean<T> {
    String code
    String message
    T data

    static <T> ResponseBean<T> success() {
        return new ResponseBean<>(code: ResponseCodeEnum.SUCCESS.code)
    }

    static <T> ResponseBean<T> success(T data) {
        return new ResponseBean<>(code: ResponseCodeEnum.SUCCESS.code, message: null as String, data: data)
    }

    static <T> ResponseBean<T> fail(ResponseCodeEnum responseCodeEnum) {
        return new ResponseBean<>(code: responseCodeEnum.code, message: responseCodeEnum.message)
    }

    static <T> ResponseBean<T> fail(ResponseCodeEnum responseCodeEnum, String message) {
        return new ResponseBean<>(code: responseCodeEnum.code, message: message)
    }
}
