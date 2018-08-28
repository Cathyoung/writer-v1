package com.slingerxv.writer.security.config

import groovy.transform.CompileStatic
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @project Common Security
 * @author frank on 12/1/17.
 */
@Order(Ordered.LOWEST_PRECEDENCE)
@Component
@CompileStatic
class CommonUnauthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String unauthenticationResponseBody = '{\n' +
                '    "code":"UNAUTHORIZED",\n' +
                '    "message":"401 UNAUTHORIZED. The request requires HTTP authentication.",\n' +
                '    "data":null\n' +
                '}'
        response.setStatus(200)
        response.setContentType('application/json;charset=UTF-8')
        response.setContentLength(unauthenticationResponseBody.getBytes().length)
        response.getOutputStream().println(unauthenticationResponseBody)
    }
}
