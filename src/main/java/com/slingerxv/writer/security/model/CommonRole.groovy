package com.slingerxv.writer.security.model

import groovy.transform.CompileStatic

/**
 * @project Common Security
 * @author frank on 11/24/17.
 */
@CompileStatic
class CommonRole implements Serializable {
    private static final long serialVersionUID = 6646148390818260548L

    Long id
    String name
    AvailabilityStatusEnum status
    String description
}
