package com.slingerxv.writer.service

import com.slingerxv.writer.dao.model.FileInfo

/**
 * Created by chenyong on 2018/9/2
 */
interface FileInfoService {

    FileInfo getFileInfoById(Integer fileInfoId)
}