package com.slingerxv.writer.config.log

import com.fasterxml.jackson.databind.ObjectMapper
import com.slingerxv.writer.constant.Constants
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.commons.lang3.StringUtils
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * common log interceptor
 *
 */
@CompileStatic
@Slf4j
@Configuration
class LogInterceptor extends HandlerInterceptorAdapter {

    private static ObjectMapper mapper = new ObjectMapper()

    @Override
    boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
        String requestURI = req.getRequestURI()
        if (StringUtils.isNotBlank(requestURI)) {
            long beginTime = System.currentTimeMillis()
            req.setAttribute("beginTime", beginTime)
            final StringBuilder requestLog = new StringBuilder("PreHandle Request - ")
            Map<String, String> requestMap = extractRequestMap(req)
            String userAgent = req.getHeader("user-agent")
            requestLog.append("[REQUEST URI:")
                    .append(requestURI).append("] [HTTP METHOD:")
                    .append(req.getMethod())
                    .append("] [PATH INFO:")
                    .append(req.getPathInfo())
            if (isGet(req)) {
                requestLog.append("] [REQUEST PARAMETERS:").append(requestMap)
            }
            requestLog.append("] [REMOTE ADDRESS:")
                    .append(req.getRemoteAddr())
                    .append("[AGENT:")
                    .append(escape(userAgent))
                    .append("] [IP:")
                    .append(escape(getIpAddress(req)))
                    .append("] [SESSION:")
                    .append(req.getSession().getId()).append("]")
            log.info(requestLog.toString())
        }
        return true
    }

    @Override
    void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object o, Exception ex)
            throws Exception {
        String requestURI = req.getRequestURI()
        if (StringUtils.isNotBlank(requestURI)) {
            long beginTime = (Long) req.getAttribute("beginTime")
            long timeConsume = System.currentTimeMillis() - beginTime
            final StringBuilder responseLog = new StringBuilder("AfterCompletion RESPONSE - ")
                    .append("[URI:")
                    .append(requestURI)
                    .append("] [HTTP METHOD:")
                    .append(req.getMethod())
                    .append("] [TIME CONSUME:")
                    .append(timeConsume).append("] [SESSION:")
                    .append(req.getSession().getId())
            if (isPost(req)) {
                responseLog.append("] [REQUEST BODY:").append(extractBody(req, Constants.REQUEST_BODY))
            }
            responseLog.append("] [RETURN:")
                    .append(extractBody(req, Constants.RESPONSE_BODY))
                    .append("]")
            log.info(responseLog.toString())
        }
    }

    /**
     * 将参数值中的“[]”进行转义
     *
     * @param value
     * @return
     */
    private static String escape(String value) {
        if (StringUtils.isNotBlank(value)) {
            return value.replace("[", "\\[").replace("]", "\\]")
        }
        return ""
    }

    /**
     * 解析IP地址
     *
     * @param request
     * @return
     */
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For")
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP")
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP")
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP")
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR")
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr()
        }
        return ip
    }

    /**
     * 解析Request的Parameters,隐藏请求中的username password参数
     *
     * @param request
     * @return
     */
    private Map<String, String> extractRequestMap(HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>()
        Enumeration<?> requestParamNames = request.getParameterNames()
        while (requestParamNames.hasMoreElements()) {
            String requestParamName = (String) requestParamNames.nextElement()
            if (!StringUtils.containsAny(requestParamName, "username", "password")) {
                String requestParamValue = request.getParameter(requestParamName)
                requestMap.put(requestParamName, requestParamValue)
            }
        }
        return requestMap
    }

    private String extractBody(HttpServletRequest req, String key) {
        if (req.getAttribute(key) != null) {
            String body = mapper.writeValueAsString(req.getAttribute(key))
            req.removeAttribute(key) //属性读完之后删除
            return StringUtils.isEmpty(body) ? "-" : body.length() > 5000 ? body.substring(0, 5000) : body
        }
        return "-"
    }

    private boolean isPost(HttpServletRequest req) {
        return "POST".equals(req.getMethod().toUpperCase())
    }

    private boolean isGet(HttpServletRequest req) {
        return "GET".equals(req.getMethod().toUpperCase())
    }

}
