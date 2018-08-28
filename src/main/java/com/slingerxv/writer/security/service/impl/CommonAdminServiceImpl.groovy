package com.slingerxv.writer.security.service.impl

import com.slingerxv.writer.security.model.CommonUserDetails
import com.slingerxv.writer.security.service.CommonSecurityService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * @project Common Security
 * @author frank on 11/25/17.
 */
@Service
@CompileStatic
class CommonAdminServiceImpl implements UserDetailsService {
    @Autowired
    CommonSecurityService commonSecurityService

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CommonUserDetails adminUser = commonSecurityService.getAdminWithAuthoritiesByUsername(username)
        if (!adminUser || !adminUser.enabled) {
            throw new UsernameNotFoundException('Username not found')
        }
        return adminUser
    }
}
