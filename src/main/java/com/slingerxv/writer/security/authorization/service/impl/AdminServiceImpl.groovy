package com.slingerxv.writer.security.authorization.service.impl

import ai.advance.common.security.model.*
import com.slingerxv.writer.security.authorization.dto.AdminWithRolesDTO
import com.slingerxv.writer.security.authorization.dto.RoleWithAuthoritiesDTO
import com.slingerxv.writer.security.authorization.service.AdminService
import com.slingerxv.writer.security.authorization.util.AuthorizationCustomException
import com.slingerxv.writer.security.model.*
import com.slingerxv.writer.security.service.CommonSecurityService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * @project audit-backend
 * @author frank on 11/27/17.
 */
@Service
@CompileStatic
class AdminServiceImpl implements AdminService {
    @Autowired
    private PasswordEncoder passwordEncoder
    @Autowired
    private CommonSecurityService commonSecurityService

    @Override
    List<CommonAuthority> getAuthorities() {
        return commonSecurityService.listAuthorities(null)
    }

    @Override
    List<RoleWithAuthoritiesDTO> getRoles() {
        List<CommonRole> roles = commonSecurityService.listRoles(null, null, null)
        return assembleRoles(roles)
    }

    @Override
    RoleWithAuthoritiesDTO getRoleById(Long roleId) {
        CommonRole role = commonSecurityService.getRoleById(roleId)
        if (!role) {
            throw new AuthorizationCustomException("Role not found with id [$roleId]")
        }
        return assembleRoles([role])[0]
    }

    @Override
    void saveRole(RoleWithAuthoritiesDTO role) {
        if (!role.name || !role.description || !role.status) {
            throw new AuthorizationCustomException('Invalid input!')
        }
        try {
            commonSecurityService.saveRole(role, role.authorities == null ? null : role.authorities.collect { it.id })
        } catch (DuplicateKeyException e) {
            throw new AuthorizationCustomException("DuplicateKeyException throwed on creating new role! Error message: [${e.message}]")
        }
    }

    @Override
    void updateRole(Long roleId, RoleWithAuthoritiesDTO role) {
        if (role.id && role.id != roleId) {
            throw new AuthorizationCustomException('Role id does not match!')
        }
        role.id = roleId
        try {
            commonSecurityService.updateRole(role, role.authorities == null ? null : role.authorities.collect { it.id })
        } catch (DuplicateKeyException e) {
            throw new AuthorizationCustomException("DuplicateKeyException throwed on updating the role! Error message: [${e.message}]")
        }
    }

    @Override
    void removeRoleById(Long roleId) {
        commonSecurityService.removeRolesByIds([roleId])
    }

    @Override
    List<AdminWithRolesDTO> getAdmins() {
        List<CommonAdmin> admins = commonSecurityService.listAdmins(null, null, null, null)
        return assembleAdmins(admins)
    }

    @Override
    void saveAdmin(AdminWithRolesDTO admin) {
        if (!admin.username || !admin.password || !admin.email || !admin.realName || !admin.mobile) {
            throw new AuthorizationCustomException('Invalid input!')
        }
        admin.password = validatePassword(admin.password)
        try {
            commonSecurityService.saveAdmin(admin, admin.roles == null ? null : admin.roles.collect { it.id })
        } catch (DuplicateKeyException e) {
            throw new AuthorizationCustomException("DuplicateKeyException throwed on creating new role! Error message: [${e.message}]")
        }
    }

    @Override
    void updateAdmin(Long adminId, AdminWithRolesDTO admin) {
        if (admin.id && admin.id != adminId) {
            throw new AuthorizationCustomException('Admin id does not match!')
        }
        admin.id = adminId

        if (admin.password != null) {
            admin.password = validatePassword(admin.password)
        }

        try {
            commonSecurityService.updateAdmin(admin, admin.roles == null ? null : admin.roles.collect { it.id })
        } catch (DuplicateKeyException e) {
            throw new AuthorizationCustomException("DuplicateKeyException throwed on updating the role! Error message: [${e.message}]")
        }
    }

    @Override
    List<CommonRole> getRolesByAdminId(Long adminId) {
        List<CommonAdminRole> adminRoles = commonSecurityService.listAdminRolesByAdminIds([adminId])
        List<Long> roleIds = adminRoles.collect { it.roleId }.unique()
        List<CommonRole> roles = roleIds ? commonSecurityService.listRoles(roleIds, null, null) : [] as List<CommonRole>
        return roles
    }

    @Override
    List<CommonAdmin> listAdminByRoleNames(Collection<String> roleNames) {
        def roles = commonSecurityService.listRoles(null, roleNames, AvailabilityStatusEnum.ENABLED)
        if (roles) {
            def roleIds = []
            roles.each {
                roleIds << it.id
            }
            def admins = commonSecurityService.listAdminsByRoleIds(roleIds)
            if (admins) {
                admins.each {it.password = null}
            }
            return admins
        }
        return null
    }

    @Override
    List<CommonAdmin> listBasicAdmins() {
        return commonSecurityService.listAdmins(null, null, null, AvailabilityStatusEnum.ENABLED)
    }

    private String validatePassword(String password) {
        if (password.length() < 6) {
            throw new AuthorizationCustomException('Invalid input!')
        }
        return passwordEncoder.encode(password)
    }

    private List<RoleWithAuthoritiesDTO> assembleRoles(List<CommonRole> roles) {
        List<RoleWithAuthoritiesDTO> roleWithAuthoritiesList = roles.collect { new RoleWithAuthoritiesDTO(it) }
        List<CommonRoleAuthority> roleAuthorities = commonSecurityService.listRoleAuthoritiesByRoleIds(roles.collect {
            it.id
        })
        List<Long> authorityIds = roleAuthorities.collect { it.authorityId }.unique()
        List<CommonAuthority> authorities = authorityIds ? commonSecurityService.listAuthorities(authorityIds) : [] as List<CommonAuthority>
        Map<Long, CommonAuthority> authoritiesMap = authorities.collectEntries { [it.id, it] }
        Map<Long, RoleWithAuthoritiesDTO> rolesMap = roleWithAuthoritiesList.collectEntries { [it.id, it] }
        roleAuthorities.each { roleAuthority ->
            rolesMap[roleAuthority.roleId].authorities.add(authoritiesMap[roleAuthority.authorityId])
        }
        return roleWithAuthoritiesList
    }

    private List<AdminWithRolesDTO> assembleAdmins(List<CommonAdmin> admins) {
        List<AdminWithRolesDTO> adminWithRolesList = admins.collect { new AdminWithRolesDTO(it) }
        List<CommonAdminRole> adminRoles = commonSecurityService.listAdminRolesByAdminIds(admins.collect {
            it.id
        })
        List<Long> roleIds = adminRoles.collect { it.roleId }.unique()
        List<CommonRole> roles = roleIds ? commonSecurityService.listRoles(roleIds, null, null) : [] as List<CommonRole>
        Map<Long, CommonRole> rolesMap = roles.collectEntries { [it.id, it] }
        Map<Long, AdminWithRolesDTO> adminsMap = adminWithRolesList.collectEntries { [it.id, it] }
        adminRoles.each { adminRole ->
            adminsMap[adminRole.adminId].roles.add(rolesMap[adminRole.roleId])
        }
        return adminWithRolesList
    }
}
