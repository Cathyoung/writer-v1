package com.slingerxv.writer.config.log

import com.slingerxv.writer.config.log.annotation.ResponseBodyLogger
import com.slingerxv.writer.constant.Constants
import com.slingerxv.writer.core.ResponseBean
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

import javax.servlet.http.HttpServletRequest
import java.lang.annotation.Annotation

/**
 * Description:
 */
@CompileStatic
@Slf4j
@ControllerAdvice(annotations = [ResponseBodyLogger.class])
class CommonResponseBodyAdvice implements ResponseBodyAdvice<ResponseBean> {

    @Override
    boolean supports(MethodParameter mp, Class<? extends HttpMessageConverter<?>> ct) {
        boolean excluded = false

        def logAdvices = mp.getMethodAnnotations()?.grep({ annotation -> (annotation as Annotation).annotationType() == ResponseBodyLogger.class })
        def logAdvice = (logAdvices && logAdvices.size() > 0) ? logAdvices.first() : null
        if (logAdvice) {
            excluded = ((ResponseBodyLogger) logAdvice).exclude()
        }

        return !excluded
    }

    @Override
    ResponseBean beforeBodyWrite(ResponseBean body, MethodParameter mp, MediaType mt,
                                 Class<? extends HttpMessageConverter<?>> ct, ServerHttpRequest req, ServerHttpResponse resp) {
        HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) req).getServletRequest()
        httpServletRequest.setAttribute(Constants.RESPONSE_BODY, body)
        return body
    }
}
