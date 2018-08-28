package com.slingerxv.writer.config

import com.slingerxv.writer.constant.enums.LogLevelEnum
import com.slingerxv.writer.constant.enums.ResponseCodeEnum
import com.slingerxv.writer.core.ResponseBean
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

import javax.servlet.http.HttpServletRequest

@ControllerAdvice
@Slf4j
class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    ResponseBean defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ResponseBean result
        if (e instanceof IllegalArgumentException) {
            result = getResponseBeanInfo(ResponseCodeEnum.PARAMETER_ERROR, e, LogLevelEnum.WARN)
        } else {
            result = getResponseBeanInfo(ResponseCodeEnum.ERROR, e, LogLevelEnum.ERROR)
        }
        return result
    }

    ResponseBean getResponseBeanInfo(ResponseCodeEnum codeEnum, Exception e, LogLevelEnum logLevelEnum) {
        switch (logLevelEnum) {
            case LogLevelEnum.INFO: log.info("info level", e); break
            case LogLevelEnum.WARN: log.warn("warn level", e); break
            case LogLevelEnum.ERROR: log.error("error level", e); break
            case LogLevelEnum.DEBUG: log.debug("debug level", e); break
        }
        return new ResponseBean(
                code: codeEnum,
                message: e.message ?: codeEnum.message,
        )
    }

}
