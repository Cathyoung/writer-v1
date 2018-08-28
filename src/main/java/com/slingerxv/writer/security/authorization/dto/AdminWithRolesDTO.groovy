package com.slingerxv.writer.security.authorization.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.slingerxv.writer.security.model.CommonAdmin
import com.slingerxv.writer.security.model.CommonRole
import groovy.transform.CompileStatic

/**
 * @project audit-backend
 * @author frank on 11/27/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@CompileStatic
class AdminWithRolesDTO extends CommonAdmin {
    private static final long serialVersionUID = 3133978019369028340L

    List<CommonRole> roles

    /**
     * You cannot remove this default constructor as there is another constructor!
     * Groovy parameter constructor works by calling the default zero-parameter constructor then setters.
     * http://groovy-lang.org/style-guide.html#_initializing_beans_with_named_parameters_and_the_default_constructor
     */
    AdminWithRolesDTO() {
    }

    AdminWithRolesDTO(CommonAdmin other) {
        this.id = other.id
        this.username = other.username
        this.email = other.email
        this.realName = other.realName
        this.mobile = other.mobile
        this.status = other.status
        this.createTimestamp = other.createTimestamp
        this.updateTimestamp = other.updateTimestamp
        this.roles = []
    }
}
