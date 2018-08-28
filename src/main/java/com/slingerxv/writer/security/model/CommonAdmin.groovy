package com.slingerxv.writer.security.model

import groovy.transform.CompileStatic

/**
 * @project Common Security
 * @author frank on 11/24/17.
 */
@CompileStatic
class CommonAdmin implements Serializable {
    private static final long serialVersionUID = 6323029393867440570L

    Long id
    String username
    String password
    String email
    String realName
    String mobile
    AvailabilityStatusEnum status
    Long createTimestamp
    Long updateTimestamp
}
