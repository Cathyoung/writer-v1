package com.slingerxv.writer.security.handler

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@CompileStatic
@Slf4j
class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                 Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response)
        if (response.isCommitted()) {
            log.debug("Response has already been committed. Unable to redirect to " + targetUrl)
            return
        }
        log.debug("forward to url:" + targetUrl)
        request.getRequestDispatcher(targetUrl).forward(request, response)

        clearAuthenticationAttributes(request)
    }
}
