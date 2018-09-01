package com.slingerxv.writer.controller

import com.slingerxv.writer.constant.enums.ResponseCodeEnum
import com.slingerxv.writer.core.ResponseBean
import groovy.transform.CompileStatic
import lombok.extern.slf4j.Slf4j
import org.springframework.validation.BindingResult

/**
 * Created by chenyong on 2018/8/26
 */
@Slf4j
@CompileStatic
class BaseController {

    static ResponseBean failedWithParameterError(BindingResult bindingResult) {
        def details = bindingResult.getFieldErrors().collect {
            it.getField().concat(' ').concat(it.getDefaultMessage())
        }
        details.addAll(bindingResult.getGlobalErrors().collect {
            it.getDefaultMessage()
        })
        return failedWithParameterError().setExtra(details)
    }

    static ResponseBean failedWithParameterError() {
        return ResponseBean.fail(ResponseCodeEnum.PARAMETER_ERROR)
    }
}
