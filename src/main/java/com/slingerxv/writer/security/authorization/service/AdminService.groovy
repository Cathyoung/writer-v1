package com.slingerxv.writer.security.authorization.service

import com.slingerxv.writer.security.authorization.dto.AdminWithRolesDTO
import com.slingerxv.writer.security.authorization.dto.RoleWithAuthoritiesDTO
import com.slingerxv.writer.security.model.CommonAdmin
import com.slingerxv.writer.security.model.CommonAuthority
import com.slingerxv.writer.security.model.CommonRole
import groovy.transform.CompileStatic

/**
 * @project audit-backend
 * @author frank on 11/27/17.
 */
@CompileStatic
interface AdminService {
    List<CommonAuthority> getAuthorities()

    List<RoleWithAuthoritiesDTO> getRoles()

    RoleWithAuthoritiesDTO getRoleById(Long roleId)

    void saveRole(RoleWithAuthoritiesDTO role)

    void updateRole(Long roleId, RoleWithAuthoritiesDTO role)

    void removeRoleById(Long roleId)

    List<AdminWithRolesDTO> getAdmins()

    void saveAdmin(AdminWithRolesDTO admin)

    void updateAdmin(Long adminId, AdminWithRolesDTO admin)

    List<CommonRole> getRolesByAdminId(Long adminId)

    List<CommonAdmin> listAdminByRoleNames(Collection<String> roleNames)

    List<CommonAdmin> listBasicAdmins()
}
