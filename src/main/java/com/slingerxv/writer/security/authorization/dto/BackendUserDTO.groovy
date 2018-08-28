package com.slingerxv.writer.security.authorization.dto

import com.slingerxv.writer.security.model.AvailabilityStatusEnum
import com.slingerxv.writer.security.model.CommonRole
import com.slingerxv.writer.security.model.CommonUserDetails
import groovy.transform.CompileStatic

/**
 * @project audit-backend
 * @author frank on 11/27/17.
 */
@CompileStatic
class BackendUserDTO implements Serializable {
    private static final long serialVersionUID = 9002639640245959638L

    Long id
    String username
    String email
    String realName
    String mobile
    AvailabilityStatusEnum status
    List<CommonRole> roles

    BackendUserDTO(CommonUserDetails other) {
        this.id = other.id
        this.username = other.username
        this.email = other.email
        this.realName = other.realName
        this.mobile = other.mobile
        this.status = other.status
    }
}
