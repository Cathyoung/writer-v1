package com.slingerxv.writer.security.authorization.controller

import com.slingerxv.writer.constant.SecurityConstants
import com.slingerxv.writer.constant.enums.ResponseCode
import com.slingerxv.writer.core.ResponseBean
import com.slingerxv.writer.security.authorization.dto.BackendUserDTO
import com.slingerxv.writer.security.authorization.service.AdminService
import com.slingerxv.writer.security.authorization.util.ContextHolder
import com.slingerxv.writer.security.model.CommonRole
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.DisabledException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.WebAttributes
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest

@RestController
@CompileStatic
@RequestMapping(SecurityConstants.ACESS_URL_HEADER)
class LoginController {

    @Autowired
    AdminService adminService

    /**
     * when login succeeded,return user data
     */
    @RequestMapping(path = SecurityConstants.LOGIN_SUCCESS)
    ResponseBean loginSuccess() {
        BackendUserDTO backendUserDTO = new BackendUserDTO(ContextHolder.currentUser)
        List<CommonRole> commonRoleList = adminService.getRolesByAdminId(backendUserDTO.id)
        backendUserDTO.roles = commonRoleList
        return ResponseBean.success(backendUserDTO)
    }

    /**
     * return login failed description
     */
    @RequestMapping(path = SecurityConstants.LOGIN_FAIL)
    ResponseBean loginFail(HttpServletRequest request) {
        AuthenticationException exception =
                (AuthenticationException) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)
        ResponseCode code = ResponseCode.LOGIN_FAILED
        if (exception != null) {
            if (exception instanceof UsernameNotFoundException) {
                code = ResponseCode.USER_NOT_FOUND
            } else if (exception instanceof DisabledException) {
                code = ResponseCode.USER_DISABLED
            }
        }
        return ResponseBean.fail(code)
    }

    /**
     * logout
     */
    @RequestMapping(path = SecurityConstants.LOGOUT_SUCCESS)
    ResponseBean logoutSuccess() {
        return ResponseBean.success()
    }
}
