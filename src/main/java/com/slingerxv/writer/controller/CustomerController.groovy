package com.slingerxv.writer.controller

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.slingerxv.writer.constant.Constants
import com.slingerxv.writer.constant.enums.CustomerType
import com.slingerxv.writer.constant.enums.DataRecoradStatus
import com.slingerxv.writer.core.ResponseBean
import com.slingerxv.writer.core.dto.CustomerDto
import com.slingerxv.writer.dao.model.Customer
import com.slingerxv.writer.security.authorization.util.ContextHolder
import com.slingerxv.writer.service.CustomerService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

/**
 * Created by chenyong on 2018/9/1
 */

@RestController
@CompileStatic
@Slf4j
@RequestMapping('writer/customer')
class CustomerController extends BaseController {

    @Autowired
    CustomerService customerService

    @PostMapping(value = '/list')
    ResponseBean list(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                      @RequestParam(value = "pageSize", required = false) Integer pageSize,
                      @RequestParam(value = "cName", required = false) String cName,
                      @RequestParam(value = "customerType", required = false) CustomerType customerType,
                      HttpServletRequest request) {
        PageInfo pageInfo = PageHelper.startPage(pageNum ?: Constants.DEFAULT_PAGE_NUM,
                pageSize ?: Constants.DEFAULT_PAGE_SIZE).doSelectPageInfo {
            customerService.listAllByTypeOrName(customerType, cName)
        }
        return ResponseBean.success(pageInfo)
    }

    @PostMapping(value = '/create')
    ResponseBean create(@Valid CustomerDto customerDto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return failedWithParameterError(bindingResult)
        }
        Customer customer = new Customer()
        BeanUtils.copyProperties(customerDto, customer)
        customer.cEnable = DataRecoradStatus.ENABLE.value as byte
        customer.creator = ContextHolder.currentUser.id as Integer
        customer.creatName = ContextHolder.currentUser.realName
        customer.createTime = System.currentTimeMillis()
        customerService.insert(customer)
        return ResponseBean.success()
    }

//    @PostMapping(value = '/update')
//    ResponseBean update(
//            @RequestParam(value = "id", required = true) Long id,
//            @RequestParam(value = "title", required = true) String title,
//            @RequestParam(value = "content", required = true) String content,
//            HttpServletRequest request) {
//        magazineService.update(id, title, content)
//        return ResponseBean.success()
//    }

    @PostMapping(value = '/delete')
    ResponseBean delete(
            @RequestParam(value = "id", required = true) Long id, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return failedWithParameterError(bindingResult)
        }
        customerService.delete(id.intValue())
        return ResponseBean.success()
    }

}