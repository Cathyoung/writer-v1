package com.slingerxv.writer.security.config

import com.slingerxv.writer.security.service.CommonSecurityService
import com.slingerxv.writer.security.service.impl.CommonAdminServiceImpl
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint

/**
 * You need to extend this class and add @EnableWebSecurity to the inherited class to make it work.
 * @project Common Security
 * @author frank on 11/25/17.
 */
@CompileStatic
abstract class CommonSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LocalAuthorityScanner localAuthorityGenerator
    @Autowired
    CommonSecurityService commonSecurityService
    @Autowired
    CommonAdminServiceImpl commonAdminService
    @Autowired
    PasswordEncoder passwordEncoder
    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        def registry = http.authorizeRequests()

        localAuthorityGenerator.findLocalAuthorities().with {
            localAuthorityGenerator.updateCommonAuthorityTable(it)
        }.each {
            registry.antMatchers(it.authority).hasAuthority(it.authority)
        }

        registry.anyRequest().authenticated()

        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)

        configureHook(http)

        http.sessionManagement()

        http.csrf().disable()
    }

    void configureHook(HttpSecurity http) {}

    /**
     * The inherited class may use auth.getConfigurer() to add more config for userDetailsService.
     * For example, you can use
     *      super.configure(auth);
     *      auth.getConfigurer(DaoAuthenticationConfigurer.class)
     *          .addObjectPostProcessor();
     * to throw UsernameNotFoundException instead of the non-specific BadCredentialsException
     * See {@link org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider#setHideUserNotFoundExceptions} for details.
     *
     * Or you can re-configure the authBuilder by not inheriting the method of this super class.
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(commonAdminService)
                .passwordEncoder(passwordEncoder)
    }
}
