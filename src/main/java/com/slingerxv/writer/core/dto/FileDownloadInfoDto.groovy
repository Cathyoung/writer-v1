package com.slingerxv.writer.core.dto

import com.slingerxv.writer.file.service.BaseFileDto
import groovy.transform.CompileStatic

import javax.validation.constraints.NotNull

/**
 * Created by chenyong on 2018/9/1
 */
@CompileStatic
class FileDownloadInfoDto extends BaseFileDto {

    /**
     * fileInfoId
     */
    @NotNull
    Integer fileInfoId

}
