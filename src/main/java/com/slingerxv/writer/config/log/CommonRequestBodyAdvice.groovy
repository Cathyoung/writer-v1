package com.slingerxv.writer.config.log

import com.slingerxv.writer.config.log.annotation.RequestBodyLogger
import com.slingerxv.writer.constant.Constants
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.commons.io.IOUtils
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpInputMessage
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice

import java.lang.annotation.Annotation
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets

/**
 * Description:
 */
@CompileStatic
@Slf4j
@ControllerAdvice(annotations = [RequestBodyLogger.class])
class CommonRequestBodyAdvice implements RequestBodyAdvice {

    @Override
    boolean supports(MethodParameter mp, Type targetType, Class<? extends HttpMessageConverter<?>> hmc) {
        boolean excluded = false

        def logAdvices = mp.getMethodAnnotations()?.grep({ annotation -> (annotation as Annotation).annotationType() == RequestBodyLogger.class })
        def logAdvice = (logAdvices && logAdvices.size() > 0) ? logAdvices.first() : null
        if (logAdvice) {
            excluded = ((RequestBodyLogger) logAdvice).exclude()
        }

        return !excluded
    }

    @Override
    Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                           Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return "-"
    }

    @Override
    HttpInputMessage beforeBodyRead(HttpInputMessage im, MethodParameter parameter, Type targetType,
                                    Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes()
        if (requestAttributes != null) {
            byte[] ba = IOUtils.toByteArray(im.getBody())
            requestAttributes.setAttribute(Constants.REQUEST_BODY, new String(ba, StandardCharsets.UTF_8), RequestAttributes.SCOPE_REQUEST)
            final ByteArrayInputStream bais = new ByteArrayInputStream(ba)
            return new HttpInputMessage() {
                @Override
                InputStream getBody() throws IOException {
                    return bais
                }

                @Override
                HttpHeaders getHeaders() {
                    return im.getHeaders()
                }
            }
        }
        return im
    }

    @Override
    Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                         Class<? extends HttpMessageConverter<?>> converterType) {
        return body
    }

}
