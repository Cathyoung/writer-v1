package com.slingerxv.writer.service.impl

import com.slingerxv.writer.core.dto.FileInfoDto
import com.slingerxv.writer.dao.mapper.FileInfoMapper
import com.slingerxv.writer.dao.model.FileInfo
import com.slingerxv.writer.file.service.BaseFileDto
import com.slingerxv.writer.file.service.FileBusinessHookService
import com.slingerxv.writer.security.authorization.util.ContextHolder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by chenyong on 2018/9/1
 */
@Service
class FileBusinessHookServiceImpl implements FileBusinessHookService {

    @Autowired
    FileInfoMapper fileInfoMapper

    @Override
    void businessHandle(BaseFileDto baseFileDto) throws Exception {
        if (baseFileDto instanceof FileInfoDto) {
            FileInfoDto fileInfoDto = (FileInfoDto) baseFileDto
            fileInfoMapper.insert(new FileInfo(
                    creator: ContextHolder.currentUser.realName,
                    createTime: System.currentTimeMillis(),
                    taskId: fileInfoDto.taskId,
                    fileName: fileInfoDto.fileName,
                    fileType: fileInfoDto.fileType.name(),
                    path: fileInfoDto.path
            ))
        }
    }
}
