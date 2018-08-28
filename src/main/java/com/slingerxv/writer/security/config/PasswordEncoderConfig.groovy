package com.slingerxv.writer.security.config

import groovy.transform.CompileStatic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder

/**
 * @project Common Security
 * @author frank on 11/29/17.
 */
@Configuration
@CompileStatic
class PasswordEncoderConfig {
    /**
     * Here we provided a default password encoder if you do not provide one.
     * @return The default password encoder
     */
    @Order(Ordered.LOWEST_PRECEDENCE)
    @Bean
    PasswordEncoder defaultPasswordEncoder() {
        return new Pbkdf2PasswordEncoder()
    }
}
