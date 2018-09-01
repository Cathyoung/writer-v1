package com.slingerxv.writer.core.dto

import com.slingerxv.writer.constant.enums.FileTypeEnum
import com.slingerxv.writer.file.service.BaseFileDto
import groovy.transform.CompileStatic

import javax.validation.constraints.NotNull

/**
 * Created by chenyong on 2018/9/1
 */
@CompileStatic
class FileInfoDto extends BaseFileDto {

    /**
     * 订单id
     */
    @NotNull
    Integer taskId

    /**
     * 文件属性-新稿件 修改稿 代写要求 修改文档等
     */
    @NotNull
    FileTypeEnum fileType

}
