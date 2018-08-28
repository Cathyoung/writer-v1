package com.slingerxv.writer.security.authorization.util

import com.slingerxv.writer.security.model.CommonUserDetails
import groovy.transform.CompileStatic
import org.springframework.security.core.context.SecurityContextHolder

/**
 * Utility class to fetch context-specific info.
 * @project audit-backend
 * @author frank on 10/10/17.
 */
@CompileStatic
class ContextHolder {
    static String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName()
    }

    static CommonUserDetails getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() as CommonUserDetails
    }
}
