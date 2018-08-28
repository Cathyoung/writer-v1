package com.slingerxv.writer.security.service

import com.slingerxv.writer.security.model.*
import groovy.transform.CompileStatic

/**
 * @project Common Security
 * @author frank on 11/24/17.
 */
@CompileStatic
interface CommonSecurityService {
    /*******************************
     *
     * Admin CRUD
     * 管理员用户增删改查
     *
     ********************************
     */

    int saveAdmin(CommonAdmin admin, Collection<Long> roleIds)

    int removeAdminsByIds(Collection<Long> adminIds)

    /**
     * If roleIds is not null, update admin-role relation. Note empty roleIds [] means clear admin-role relations of the admin
     * 如果 roleIds 不是空指针，更新 admin-role 关系。注意 roleIds 若是空集合，相当于清空 admin 的 admin-role 关系
     * @param admin
     * @param roleIds
     * @return
     */
    int updateAdmin(CommonAdmin admin, Collection<Long> roleIds)

    List<CommonAdmin> listAdmins(Collection<Long> adminIds, Collection<String> usernames, Collection<String> realNames, AvailabilityStatusEnum status)

    List<CommonAdmin> listAdminsByUsernames(Collection<String> usernames)

    List<CommonAdmin> listAdminsByRoleIds(Collection<Long> roleIds)

    /*******************************
     *
     * Role CRUD
     * 角色增删改查
     *
     ********************************
     */

    int saveRole(CommonRole role, Collection<Long> authorityIds)

    int removeRolesByIds(Collection<Long> roleIds)

    /**
     * If authorityIds is not null, update role-authority relation. Note empty authorityIds [] means clear role-authority relations of the role
     * 如果 authorityIds 不是空指针，更新 role-authority 关系。注意 authorityIds 若是空集合，相当于清空 role 的 role-authority 关系
     * @param role
     * @param authorityIds
     * @return
     */
    int updateRole(CommonRole role, Collection<Long> authorityIds)

    List<CommonRole> listRoles(Collection<Long> roleIds, Collection<String> roleNames, AvailabilityStatusEnum status)

    CommonRole getRoleById(Long roleId)

    /*******************************
     *
     * Authority RU
     * 权限改查
     *
     ********************************
     */

    List<CommonAuthority> listAuthorities(Collection<Long> authorityIds)

    List<CommonAuthority> listAuthoritiesByRoleIds(Collection<Long> roleIds)

    /*******************************
     *
     * Admin-Role R
     * 管理员-角色关系查询
     *
     ********************************
     */

    List<CommonAdminRole> listAdminRolesByAdminIds(List<Long> adminIds)

    List<CommonAdminRole> listAdminRoleByRoleIds(List<Long> roleIds)

    /*******************************
     *
     * Role-Authority R
     * 角色-权限关系查询
     *
     ********************************
     */

    List<CommonRoleAuthority> listRoleAuthoritiesByRoleIds(List<Long> roleIds)

    List<CommonRoleAuthority> listRoleAuthoritiesByAuthorityIds(List<Long> authorityIds)

    /*******************************
     *
     * Other utility functions
     * 其他工具函数
     *
     ********************************
     */

    CommonUserDetails getAdminWithAuthoritiesByUsername(String username)
}
