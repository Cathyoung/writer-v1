package com.slingerxv.writer.security.authorization.controller

import com.slingerxv.writer.constant.enums.ResponseCode
import com.slingerxv.writer.core.ResponseBean
import com.slingerxv.writer.security.authorization.dto.AdminWithRolesDTO
import com.slingerxv.writer.security.authorization.dto.RoleWithAuthoritiesDTO
import com.slingerxv.writer.security.authorization.service.AdminService
import com.slingerxv.writer.security.authorization.util.AuthorizationCustomException
import com.slingerxv.writer.security.authorization.util.ContextHolder
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * @project audit-backend
 * @author frank on 11/27/17.
 */
@Slf4j
@RestController
@RequestMapping("/audit-web/admin")
@CompileStatic
class AdminController {
    @Autowired
    AdminService adminService

    @GetMapping('/authorities')
    ResponseBean getAuthorities() {
        return ResponseBean.success(adminService.getAuthorities())
    }

    @GetMapping('/roles')
    ResponseBean getRoles() {
        return ResponseBean.success(adminService.getRoles())
    }

    @GetMapping('/roles/{roleId}')
    ResponseBean getRole(@PathVariable('roleId') Long roleId) {
        return ResponseBean.success(adminService.getRoleById(roleId))
    }

    @PostMapping('/roles/add')
    ResponseBean addRole(@RequestBody RoleWithAuthoritiesDTO role) {
        try {
            adminService.saveRole(role)
        } catch (AuthorizationCustomException e) {
            return ResponseBean.fail(ResponseCode.ERROR, e.message)
        }
        return ResponseBean.success()
    }

    @PostMapping('/roles/{roleId}/update')
    ResponseBean updateRole(@PathVariable('roleId') Long roleId, @RequestBody RoleWithAuthoritiesDTO role) {
        try {
            adminService.updateRole(roleId, role)
        } catch (AuthorizationCustomException e) {
            return ResponseBean.fail(ResponseCode.ERROR, e.message)
        }
        return ResponseBean.success()
    }

    @PostMapping('/roles/{roleId}/delete')
    ResponseBean deleteRole(@PathVariable('roleId') Long roleId) {
        adminService.removeRoleById(roleId)
        return ResponseBean.success()
    }

    @GetMapping('/users')
    ResponseBean getAdmins() {
        return ResponseBean.success(adminService.getAdmins())
    }

    @PostMapping('/users/add')
    ResponseBean addAdmin(@RequestBody AdminWithRolesDTO admin) {
        try {
            adminService.saveAdmin(admin)
        } catch (AuthorizationCustomException e) {
            return ResponseBean.fail(ResponseCode.ERROR, e.message)
        }
        return ResponseBean.success()
    }

    @PostMapping('/users/{userId}/update')
    ResponseBean updateAdmin(@PathVariable('userId') Long userId, @RequestBody AdminWithRolesDTO admin) {
        try {
            adminService.updateAdmin(userId, admin)
        } catch (AuthorizationCustomException e) {
            return ResponseBean.fail(ResponseCode.ERROR, e.message)
        }
        return ResponseBean.success()
    }

    /**
     * 获取客服（暂时按这个角色获取）
     * @return
     */
    @GetMapping('/basic/users')
    ResponseBean getBasicUsers(@RequestParam(name = "all", required = false) Integer all) {
        def admins = adminService.listBasicAdmins()
        if (1 == all) {
            return ResponseBean.success(admins)
        } else {
            def currentUserId = ContextHolder.currentUser.id
            def otherCollectors = []
            admins.each {
                if (currentUserId != it.id) {
                    otherCollectors << it
                }
            }
            return ResponseBean.success(otherCollectors)
        }
    }
}
