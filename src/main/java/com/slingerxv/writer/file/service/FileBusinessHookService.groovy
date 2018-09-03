package com.slingerxv.writer.file.service

/**
 * Created by chenyong on 2018/9/1
 */
interface FileBusinessHookService {
    void businessUploadHandle(BaseFileDto baseFileDto) throws Exception

    void businessDownloadHandle(BaseFileDto baseFileDto) throws Exception

}
