package com.slingerxv.writer.security.model

import groovy.transform.CompileStatic

/**
 * @project Common Security
 * @author frank on 11/24/17.
 */
@CompileStatic
class CommonAdminRole implements Serializable {
    private static final long serialVersionUID = 9046720548579152674L

    Long adminId
    Long roleId
}
