package com.slingerxv.writer.controller

import com.alibaba.fastjson.JSON
import com.slingerxv.writer.constant.enums.ResponseCode
import com.slingerxv.writer.core.ResponseBean
import com.slingerxv.writer.core.dto.FileDownloadInfoDto
import com.slingerxv.writer.core.dto.FileUploadInfoDto
import com.slingerxv.writer.dao.model.FileInfo
import com.slingerxv.writer.file.service.FileUploadService
import com.slingerxv.writer.service.FileInfoService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid
import java.nio.file.NoSuchFileException

/**
 * Created by chenyong on 2018/9/1
 */

@RestController
@CompileStatic
@Slf4j
@RequestMapping('writer/file')
class FileController extends BaseController {

    @Autowired
    FileUploadService fileUploadService

    @Autowired
    FileInfoService fileInfoService

    // 上传本地服务器`
    @PostMapping(value = '/uploadlocal')
    ResponseBean uploadlocal(
            @Valid FileUploadInfoDto fileUploadInfoDto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return failedWithParameterError(bindingResult)
        }
        return fileUploadService.fileUpload(fileUploadInfoDto, request)
    }

    // 下载
    @GetMapping(value = '/download')
    ResponseBean download(
            @Valid FileDownloadInfoDto fileDownloadInfoDto, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return failedWithParameterError(bindingResult)
        }
        FileInfo fileInfo = fileInfoService.getFileInfoById(fileDownloadInfoDto.fileInfoId)
        if (fileInfo) {
            fileDownloadInfoDto.path = fileInfo.path
            fileDownloadInfoDto.fileName = fileInfo.fileName
            try {
                fileUploadService.fileDownload(fileDownloadInfoDto, response)
                return ResponseBean.success()
            } catch (NoSuchFileException e) {
                log.error("下载文件不存在,requestDto=" + JSON.toJSONString(fileDownloadInfoDto), e)
                return ResponseBean.fail(ResponseCode.FILE_NOT_FIND)
            } catch (Exception e) {
                log.error("下载文件异常,requestDto=" + JSON.toJSONString(fileDownloadInfoDto), e)
                return ResponseBean.fail(ResponseCode.ERROR, '文件下载失败')
            }
        }
        return ResponseBean.fail(ResponseCode.FILE_NOT_FIND)
    }
}