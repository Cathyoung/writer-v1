package com.slingerxv.writer.security.model

import groovy.transform.CompileStatic
import org.springframework.security.core.userdetails.UserDetails

/**
 * @project Common Security
 * @author frank on 11/27/17.
 */
@CompileStatic
class CommonUserDetails extends CommonAdmin implements UserDetails {
    private static final long serialVersionUID = 1833368584087328888L

    List<CommonAuthority> authorities

    @Override
    void setUsername(String username) {
        super.setUsername(username)
    }

    @Override
    void setPassword(String password) {
        super.setPassword(password)
    }

    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return status == AvailabilityStatusEnum.ENABLED
    }

    CommonUserDetails(CommonAdmin other) {
        this.id = other.id
        this.username = other.username
        this.password = other.password
        this.email = other.email
        this.realName = other.realName
        this.mobile = other.mobile
        this.status = other.status
        this.createTimestamp = other.createTimestamp
        this.updateTimestamp = other.updateTimestamp
        this.authorities = []
    }
}
