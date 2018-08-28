package com.slingerxv.writer.security.model

import groovy.transform.CompileStatic

/**
 * @project Common Security
 * @author frank on 11/24/17.
 */
@CompileStatic
class CommonRoleAuthority implements Serializable {
    private static final long serialVersionUID = 3879345171328253617L

    Long roleId
    Long authorityId
}
