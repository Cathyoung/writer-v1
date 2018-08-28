package com.slingerxv.writer.config

import com.slingerxv.writer.constant.SecurityConstants
import com.slingerxv.writer.security.config.CommonSecurityConfig
import com.slingerxv.writer.security.handler.CustomLogoutSuccessHandler
import com.slingerxv.writer.security.handler.LoginSuccessHandler
import groovy.transform.CompileStatic
import org.apache.commons.lang3.StringUtils
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Order(100)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@CompileStatic
class SecurityConfig extends CommonSecurityConfig {
    static final String LOGIN_SUCCESS = SecurityConstants.ACESS_URL_HEADER + SecurityConstants.LOGIN_SUCCESS
    static final String LOGOUT_SUCCESS = SecurityConstants.ACESS_URL_HEADER + SecurityConstants.LOGOUT_SUCCESS
    static final String LOGIN_FAIL = SecurityConstants.ACESS_URL_HEADER + SecurityConstants.LOGIN_FAIL
    static final String LOGIN = SecurityConstants.ACESS_URL_HEADER + SecurityConstants.LOGIN
    static final String LOGOUT = SecurityConstants.ACESS_URL_HEADER + SecurityConstants.LOGOUT

    @Override
    void configure(HttpSecurity http) throws Exception {
        def registry = http.authorizeRequests()

        localAuthorityGenerator.findLocalAuthorities().with {
            localAuthorityGenerator.updateCommonAuthorityTable(it)
        }.each {
            registry.antMatchers(it.authority).hasAuthority(it.authority)
        }

        registry.anyRequest().authenticated()

        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
        http.formLogin()
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .loginPage(LOGIN)
                .permitAll()
        http.logout()
                .logoutUrl(LOGOUT)
                .logoutSuccessHandler(logoutHandler())
                .permitAll()
        http.sessionManagement()
                .and()
                .csrf()
                .disable()
        http.authorizeRequests()
                .antMatchers(LOGIN_SUCCESS, LOGOUT_SUCCESS, LOGIN_FAIL, SecurityConstants.HEALTH)
                .permitAll()
                .anyRequest()
                .authenticated()

    }

    @Bean
    SimpleUrlAuthenticationFailureHandler failureHandler() {
        SimpleUrlAuthenticationFailureHandler failHandler = new SimpleUrlAuthenticationFailureHandler()
        failHandler.setUseForward(false)
        failHandler.setDefaultFailureUrl(LOGIN_FAIL)
        failHandler.setRedirectStrategy(auditWebRedirectStrategy())
        return failHandler
    }

    @Bean
    LoginSuccessHandler successHandler() {
        LoginSuccessHandler successHandler = new LoginSuccessHandler()
        successHandler.setDefaultTargetUrl(LOGIN_SUCCESS)
        successHandler.setAlwaysUseDefaultTargetUrl(true)
        successHandler.setRedirectStrategy(auditWebRedirectStrategy())
        return successHandler
    }

    @Bean
    CustomLogoutSuccessHandler logoutHandler() {
        CustomLogoutSuccessHandler handler = new CustomLogoutSuccessHandler()
        handler.setDefaultTargetUrl(LOGOUT_SUCCESS)
        handler.setRedirectStrategy(auditWebRedirectStrategy())
        return handler
    }

    @Bean
    RedirectStrategy auditWebRedirectStrategy() {
        RedirectStrategy strategy = new RedirectStrategy() {
            @Override
            void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
                if (!StringUtils.contains(url, "://") && !StringUtils.startsWith(url, "//")) {
                    url = request.getContextPath() + url
                }
                response.addHeader(SecurityConstants.REDIRECT_302_LOCATION, response.encodeRedirectURL(url))
                response.setStatus(SecurityConstants.REDIRECT_302)
            }
        }
        return strategy
    }
}
