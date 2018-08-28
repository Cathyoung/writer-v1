package com.slingerxv.writer.security.authorization.dto

import com.slingerxv.writer.security.model.CommonAuthority
import com.slingerxv.writer.security.model.CommonRole
import groovy.transform.CompileStatic

/**
 * @project audit-backend
 * @author frank on 11/27/17.
 */
@CompileStatic
class RoleWithAuthoritiesDTO extends CommonRole {
    private static final long serialVersionUID = 5740075580515667411L

    List<CommonAuthority> authorities

    /**
     * You cannot remove this default constructor as there is another constructor!
     * Groovy parameter constructor works by calling the default zero-parameter constructor then setters.
     * http://groovy-lang.org/style-guide.html#_initializing_beans_with_named_parameters_and_the_default_constructor
     */
    RoleWithAuthoritiesDTO() {
    }

    RoleWithAuthoritiesDTO(CommonRole other) {
        this.id = other.id
        this.name = other.name
        this.status = other.status
        this.description = other.description
        this.authorities = []
    }
}
