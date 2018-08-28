package com.slingerxv.writer.security.model

import groovy.transform.CompileStatic
import org.springframework.security.core.GrantedAuthority

/**
 * @project Common Security
 * @author frank on 11/23/17.
 */
@CompileStatic
class CommonAuthority implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 2170464529950854333L

    Long id
    String name
    String authority
    String description

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        CommonAuthority that = (CommonAuthority) o

        if (authority != that.authority) return false

        return true
    }

    int hashCode() {
        return (authority != null ? authority.hashCode() : 0)
    }
}
