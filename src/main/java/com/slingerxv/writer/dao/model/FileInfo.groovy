package com.slingerxv.writer.dao.model

import com.slingerxv.writer.constant.enums.FileType
import groovy.transform.CompileStatic

/**
 * 上传文件信息
 * Created by chenyong on 2018/9/1
 */
@CompileStatic
class FileInfo {
    Long id

    /**
     * 订单号
     */
    Integer taskId

    /**
     * 服务器路径
     */
    String path

    /**
     * 文件属性-新稿件 修改稿 代写要求 修改文档等
     */
    FileType fileType

    /**
     * 文件名
     */
    String fileName

    String creator

    /**
     * 下载次数
     */
    Integer downloadCount

    Long createTime
}
