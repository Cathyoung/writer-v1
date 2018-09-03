package com.slingerxv.writer.dao.mapper

import com.slingerxv.writer.dao.model.FileInfo
import groovy.transform.CompileStatic
import org.apache.ibatis.annotations.Param

/**
 * Created by chenyong on 2018/9/1
 */
@CompileStatic
interface FileInfoMapper {
    void insert(FileInfo fileInfo)

    FileInfo getFileInfoById(@Param("fileInfoId") Integer fileInfoId)

    void updateDownloadCountById(@Param("fileInfoId") Integer fileInfoId)
}
