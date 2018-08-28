package com.slingerxv.writer.security.mapper

import com.slingerxv.writer.security.model.*
import groovy.transform.CompileStatic
import org.apache.ibatis.annotations.Param

/**
 * @project Common Security
 * @author frank on 11/24/17.
 */
@CompileStatic
interface CommonSecurityMapper {
    int saveAdmin(CommonAdmin admin)

    int removeAdmins(@Param('adminIds') Collection<Long> adminIds)

    int updateAdmin(CommonAdmin admin)

    List<CommonAdmin> listAdmins(@Param('adminIds') Collection<Long> adminIds,
                                 @Param('usernames') Collection<String> usernames,
                                 @Param('realNames') Collection<String> realNames,
                                 @Param('status') AvailabilityStatusEnum status)

    List<CommonAdmin> listAdminsByRoleIds(@Param('roleIds') Collection<Long> roleIds)

    List<CommonRole> listRolesByAdminIds(@Param('adminIds') Collection<Long> adminIds)

    int saveAdminRoles(@Param('adminRoles') Collection<CommonAdminRole> adminRoles)

    int removeAdminRoles(@Param('adminIds') Collection<Long> adminIds,
                         @Param('roleIds') Collection<Long> roleIds)

    List<CommonAdminRole> listAdminRoles(@Param('adminIds') Collection<Long> adminIds,
                                         @Param('roleIds') Collection<Long> roleIds)

    int saveRole(CommonRole role)

    int removeRoles(@Param('roleIds') Collection<Long> roleIds)

    int updateRole(CommonRole role)

    List<CommonRole> listRoles(@Param('roleIds') Collection<Long> roleIds,
                               @Param('roleNames') Collection<String> roleNames,
                               @Param('status') AvailabilityStatusEnum status)

    List<CommonRole> listRolesByAuthorityIds(@Param('authorityIds') Collection<Long> authorityIds)

    int saveRoleAuthorities(@Param('roleAuthorities') Collection<CommonRoleAuthority> roleAuthorities)

    int removeRoleAuthorities(@Param('roleIds') Collection<Long> roleIds,
                              @Param('authorityIds') Collection<Long> authorityIds)

    List<CommonRoleAuthority> listRoleAuthorities(@Param('roleIds') Collection<Long> roleIds,
                                                  @Param('authorityIds') Collection<Long> authorityIds)

    int saveAuthority(CommonAuthority authority)

    List<CommonAuthority> listAuthorities(@Param('authorityIds') Collection<Long> authorityIds)

    List<CommonAuthority> listAuthoritiesByRoleIds(@Param('roleIds') Collection<Long> roleIds)

    long getLastInsertId()
}
