package com.slingerxv.writer.file.service.impl

import com.slingerxv.writer.constant.enums.ResponseCodeEnum
import com.slingerxv.writer.core.ResponseBean
import com.slingerxv.writer.file.service.BaseFileDto
import com.slingerxv.writer.file.service.FileBusinessHookService
import com.slingerxv.writer.file.service.FileUploadService
import com.slingerxv.writer.utils.FileGeneratorUtils
import com.slingerxv.writer.utils.TimeUtils
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.commons.fileupload.servlet.ServletFileUpload
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate

/**
 * Created by chenyong on 2018/9/1
 */
@Slf4j
@Service
@CompileStatic
class FileUploadServiceImpl implements FileUploadService, ApplicationContextAware {

    @Value('${fileconfig.uploadFilePath}')
    String uploadFilePath

    Map<String, FileBusinessHookService> map

    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //根据接口类型返回相应的所有bean
        map = applicationContext.getBeansOfType(FileBusinessHookService.class)
    }

    @Override
    ResponseBean fileUpload(BaseFileDto baseFileDto, HttpServletRequest request) throws IOException {
        //检查请求是否是multipart/form-data类型
        if (!ServletFileUpload.isMultipartContent(request)) {
            return ResponseBean.fail(ResponseCodeEnum.ERROR, '表单的enctype属性不是multipart/form-data类型')
        }
        StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request
        Iterator<String> iterator = req.getFileNames()
        //目前支持单个上传
        if (req.getFileMap().size() != 1) {
            return ResponseBean.fail(ResponseCodeEnum.ERROR, '上传文件个数只能1个')
        }
        def message
        while (iterator.hasNext()) {
            message = handleUploadField(req.getFile(iterator.next()), baseFileDto)
        }
        return message ? ResponseBean.fail(ResponseCodeEnum.ERROR, message as String) : ResponseBean.success()
    }

    @Override
    void fileDownload(String filePath, String fileName, HttpServletResponse response) throws IOException {

    }

    /**
     * 处理文件的表单域
     * @param item
     */
    private String handleUploadField(MultipartFile item, BaseFileDto baseFileDto) {
        String fileName = item.getOriginalFilename()  //得到上传文件的文件名
        Path path
        try {
            //上传文件存储路径
            path = Paths.get(getChildDirectory(uploadFilePath, fileName).getPath())
            Files.write(path, item.getBytes())
        } catch (Exception e) {
            //删除之前上传成功的文件
            log.error('文件上传失败', e)
            return '文件上传失败'
        }
        //业务处理
        try {
            baseFileDto.path = path.toFile().path
            baseFileDto.fileName = fileName
            map.each {
                it.value.businessHandle(baseFileDto)
            }
        } catch (Exception e) {
            //删除之前上传成功的文件
            log.error('文件上传业务操作失败', e)
            return '文件上传业务操作失败'
        }
        return null
    }

    /**
     * 按照时间创建子目录，防止一个目录中文件过多，不利于以后遍历查找
     * @param path
     * @return
     */
    private File getChildDirectory(String path, String fileName) {
        String time = TimeUtils.convertLocatDateToDateString(LocalDate.now())
        File file = new File(path, time)
        if (!file.exists()) {
            file.mkdirs()
        }
        return new File(file.toString(), FileGeneratorUtils.fileGenerateId() + fileName)
    }

    private static void delete(File f) {
        if (!f.exists()) {
            return
        }
        File[] ff = f.listFiles()
        for (File __f : ff) {
            if (__f.isDirectory()) {
                delete(__f)
            }
            __f.delete()
        }
    }
}
