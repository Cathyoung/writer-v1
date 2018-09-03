package com.slingerxv.writer.service.impl

import com.slingerxv.writer.core.dto.FileDownloadInfoDto
import com.slingerxv.writer.core.dto.FileUploadInfoDto
import com.slingerxv.writer.dao.mapper.FileInfoMapper
import com.slingerxv.writer.dao.model.FileInfo
import com.slingerxv.writer.file.service.BaseFileDto
import com.slingerxv.writer.file.service.FileBusinessHookService
import com.slingerxv.writer.security.authorization.util.ContextHolder
import com.slingerxv.writer.service.FileInfoService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by chenyong on 2018/9/1
 */
@Service
@CompileStatic
class FileInfoServiceImpl implements FileBusinessHookService, FileInfoService {

    @Autowired
    FileInfoMapper fileInfoMapper

    @Override
    void businessUploadHandle(BaseFileDto baseFileDto) throws Exception {
        if (baseFileDto instanceof FileUploadInfoDto) {
            FileUploadInfoDto fileUploadInfoDto = (FileUploadInfoDto) baseFileDto
            fileInfoMapper.insert(new FileInfo(
                    creator: ContextHolder.currentUser.realName,
                    createTime: System.currentTimeMillis(),
                    taskId: fileUploadInfoDto.taskId,
                    fileName: fileUploadInfoDto.fileName,
                    fileType: fileUploadInfoDto.fileType,
                    path: fileUploadInfoDto.path
            ))
        }
    }

    @Override
    void businessDownloadHandle(BaseFileDto baseFileDto) throws Exception {
        if (baseFileDto instanceof FileDownloadInfoDto) {
            fileInfoMapper.updateDownloadCountById(((FileDownloadInfoDto) baseFileDto).fileInfoId)
        }
    }

    @Override
    FileInfo getFileInfoById(Integer fileInfoId) {
        return fileInfoMapper.getFileInfoById(fileInfoId)
    }
}
