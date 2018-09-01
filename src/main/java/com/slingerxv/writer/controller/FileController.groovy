package com.slingerxv.writer.controller

import com.slingerxv.writer.core.ResponseBean
import com.slingerxv.writer.core.dto.FileInfoDto
import com.slingerxv.writer.file.service.FileUploadService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

/**
 * Created by chenyong on 2018/9/1
 */

@RestController
@CompileStatic
@RequestMapping('/file')
class FileController extends BaseController {

    @Autowired
    FileUploadService fileUploadService

    // 上传本地服务器`
    @PostMapping(value = '/uploadlocal')
    ResponseBean uploadlocal(@Valid FileInfoDto fileInfoDto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return failedWithParameterError(bindingResult)
        }
        return fileUploadService.fileUpload(fileInfoDto, request)
    }

    // 下载
    @PostMapping(value = '/download')
    ResponseBean download(FileInfoDto fileBaseDto, HttpServletRequest request) {
        return fileUploadService.fileUpload(fileBaseDto, request)
    }
}
