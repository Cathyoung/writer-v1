package com.slingerxv.writer.controller

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.slingerxv.writer.constant.Constants
import com.slingerxv.writer.core.ResponseBean
import com.slingerxv.writer.service.MagazineService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest

/**
 * Created by chenyong on 2018/9/1
 */

@RestController
@CompileStatic
@Slf4j
@RequestMapping('/magazine')
class MagazineController extends BaseController {

    @Autowired
    MagazineService magazineService

    @PostMapping(value = '/list')
    ResponseBean list(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                      @RequestParam(value = "pageSize", required = false) Integer pageSize,
                      @RequestParam(value = "title", required = false) String title,
                      @RequestParam(value = "createStartTime", required = false) String createStartTime,
                      @RequestParam(value = "createEndTime", required = false) String createEndTime,
                      HttpServletRequest request) {
        PageInfo pageInfo = PageHelper.startPage(pageNum ?: Constants.DEFAULT_PAGE_NUM,
                pageSize ?: Constants.DEFAULT_PAGE_SIZE).doSelectPageInfo {
            magazineService.listAllByTitle(title, createStartTime, createEndTime)
        }
        return ResponseBean.success(pageInfo)
    }

    @PostMapping(value = '/create')
    ResponseBean create(
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            HttpServletRequest request) {
        magazineService.insert(title, content)
        return ResponseBean.success()
    }

    @PostMapping(value = '/update')
    ResponseBean update(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            HttpServletRequest request) {
        magazineService.update(id, title, content)
        return ResponseBean.success()
    }

    @PostMapping(value = '/delete')
    ResponseBean delete(
            @RequestParam(value = "id", required = true) Long id, HttpServletRequest request) {
        magazineService.delete(id)
        return ResponseBean.success()
    }

}