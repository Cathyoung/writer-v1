package com.slingerxv.writer.file.service

import com.slingerxv.writer.core.ResponseBean
import groovy.transform.CompileStatic

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by chenyong on 2018/9/1
 */
@CompileStatic
interface FileUploadService {
    ResponseBean fileUpload(BaseFileDto baseFileDto, HttpServletRequest request)

    void fileDownload(BaseFileDto baseFileDto, HttpServletResponse response) throws Exception
}