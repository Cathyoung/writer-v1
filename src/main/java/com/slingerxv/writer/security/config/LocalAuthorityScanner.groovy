package com.slingerxv.writer.security.config

import com.slingerxv.writer.security.mapper.CommonSecurityMapper
import com.slingerxv.writer.security.model.CommonAuthority
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

/**
 * Fetch all mappings provided by this Spring MVC project and / or update to sql table common_authority
 * @project Common Security
 * @author frank on 11/24/17.
 */
@Component
@CompileStatic
class LocalAuthorityScanner {
    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping
    @Autowired
    CommonSecurityMapper commonSecurityMapper

    List<CommonAuthority> findLocalAuthorities() throws IllegalStateException {
        requestMappingHandlerMapping.handlerMethods.collect {
            RequestMappingInfo requestMappingInfo = it.key
            HandlerMethod handlerMethod = it.value
            if (requestMappingInfo.patternsCondition.patterns.size() != 1) {
                throw new IllegalStateException("You should not annotate empty or multiple patterns to the same method: [${it}]")
            }

            String methodName = handlerMethod.method.name
            String authorityUrl = requestMappingInfo.patternsCondition.patterns.first()

            return new CommonAuthority(name: methodName, authority: authorityUrl)
        }
    }

    List<CommonAuthority> updateCommonAuthorityTable(List<CommonAuthority> commonAuthorities) {
        def savedAuthorities = commonSecurityMapper.listAuthorities(null)
        def intersectAuthorities = commonAuthorities.intersect(savedAuthorities)

        def deprecatedAuthorities = savedAuthorities - intersectAuthorities
        def newAuthorities = commonAuthorities - savedAuthorities


        deprecatedAuthorities.each {
            if (!it.description?.startsWith('Deprecated')) {
                it.description = "Deprecated since ${new Date()}"
                commonSecurityMapper.saveAuthority(it)
            }
        }

        newAuthorities.each {
            it.description = "Added since ${new Date()}"
            commonSecurityMapper.saveAuthority(it)
        }

        return commonAuthorities
    }
}
