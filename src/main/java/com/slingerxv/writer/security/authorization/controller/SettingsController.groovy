package com.slingerxv.writer.security.authorization.controller

import com.slingerxv.writer.constant.enums.ResponseCodeEnum
import com.slingerxv.writer.core.ResponseBean
import com.slingerxv.writer.security.authorization.util.AuthorizationCustomException
import com.slingerxv.writer.security.authorization.util.ContextHolder
import com.slingerxv.writer.security.model.CommonAdmin
import com.slingerxv.writer.security.model.CommonUserDetails
import com.slingerxv.writer.security.service.CommonSecurityService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @project audit-backend
 * @author frank on 11/27/17.
 */
@RestController
@RequestMapping("/audit-web/settings")
@CompileStatic
class SettingsController {
    @Autowired
    CommonSecurityService commonSecurityService
    @Autowired
    PasswordEncoder passwordEncoder
    /**
     * \{*     "oldPassword": "2hsad78123",
     *     "password": "changeMe123",
     * \}*
     * @param requestBody
     * @return
     */
    @PostMapping('/change-password')
    ResponseBean changePassword(@RequestBody Map<String, String> requestBody) {
        CommonUserDetails currentUser = ContextHolder.currentUser
        if (!passwordEncoder.matches(requestBody['oldPassword'], currentUser.password)) {
            return ResponseBean.fail(ResponseCodeEnum.ERROR, 'Current password is wrong!')
        }
        try {
            CommonAdmin user = new CommonAdmin(id: currentUser.id, password: passwordEncoder.encode(requestBody['password']))
            commonSecurityService.updateAdmin(user, null)
        } catch (DuplicateKeyException e) {
            throw new AuthorizationCustomException("DuplicateKeyException throwed on updating the role! Error message: [${e.message}]")
        }
        return ResponseBean.success()
    }
}
