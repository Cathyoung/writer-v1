package com.slingerxv.writer.config

import com.slingerxv.writer.constant.enums.LogLevel
import com.slingerxv.writer.constant.enums.ResponseCode
import com.slingerxv.writer.core.ResponseBean
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartException

import javax.servlet.http.HttpServletRequest

@ControllerAdvice
@Slf4j
class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    ResponseBean defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ResponseBean result
        if (e instanceof IllegalArgumentException) {
            result = getResponseBeanInfo(ResponseCode.PARAMETER_ERROR, e, LogLevel.WARN)
        } else if (e instanceof MultipartException) {
            result = getResponseBeanInfo(ResponseCode.MAX_FILE_SISE, e, LogLevel.ERROR)
        } else {
            result = getResponseBeanInfo(ResponseCode.ERROR, e, LogLevel.ERROR)
        }
        return result
    }

    ResponseBean getResponseBeanInfo(ResponseCode codeEnum, Exception e, LogLevel logLevelEnum) {
        switch (logLevelEnum) {
            case LogLevel.INFO: log.info("info level", e); break
            case LogLevel.WARN: log.warn("warn level", e); break
            case LogLevel.ERROR: log.error("error level", e); break
            case LogLevel.DEBUG: log.debug("debug level", e); break
        }
        return new ResponseBean(
                code: codeEnum,
                message: e.message ?: codeEnum.message,
        )
    }

}
