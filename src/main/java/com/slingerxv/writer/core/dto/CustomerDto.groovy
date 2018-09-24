package com.slingerxv.writer.core.dto

import com.slingerxv.writer.constant.enums.CustomerType
import groovy.transform.CompileStatic
import org.hibernate.validator.constraints.NotBlank

import javax.validation.constraints.NotNull

/**
 * 客户信息
 * Created by chenyong on 2018/9/1
 */
@CompileStatic
class CustomerDto {
    Integer id
    /**
     * 客户名称
     */
    @NotBlank
    String cName

    @NotBlank
    String cMobile

    /**
     * 写发客户还是写作客户
     */
    @NotNull
    CustomerType cType

    String cQq

    /**
     * 微信号
     */
    String cWechat

    /**
     * 地区
     */
    String cRegion

    /**
     * 描述
     */
    String cDescription

    /**
     * 职称
     */
    String cTitle

    /**
     * 科室
     */
    String cDepartment

    /**
     * 医院
     */
    String cHospital

    /**
     * 难度系数
     */
    Byte cDifficulty

}
