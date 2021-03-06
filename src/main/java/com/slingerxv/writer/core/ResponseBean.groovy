package com.slingerxv.writer.core

import com.slingerxv.writer.constant.enums.ResponseCode

/**
 * Created by chenyong on 2018/8/26
 */
class ResponseBean<T> {
    String code
    String message
    T data
    Object extra

    static <T> ResponseBean<T> success() {
        return new ResponseBean<>(code: ResponseCode.SUCCESS.code)
    }

    static <T> ResponseBean<T> success(T data) {
        return new ResponseBean<>(code: ResponseCode.SUCCESS.code, message: null as String, data: data)
    }

    static <T> ResponseBean<T> fail(ResponseCode responseCodeEnum) {
        return new ResponseBean<>(code: responseCodeEnum.code, message: responseCodeEnum.message)
    }

    static <T> ResponseBean<T> fail(ResponseCode responseCodeEnum, String message) {
        return new ResponseBean<>(code: responseCodeEnum.code, message: message)
    }

    ResponseBean setExtra(Object extra) {
        if (extra != null && extra instanceof CharSequence) {
            extra = extra.toString();
        }
        this.extra = extra;
        return this;
    }
}
