package com.slingerxv.writer.service

import com.slingerxv.writer.constant.enums.CustomerType
import com.slingerxv.writer.dao.model.Customer

/**
 * Created by chenyong on 2018/9/2
 */
interface CustomerService {
    void insert(Customer customer)

//    void update(Long id, String title, String content)
//
//    void delete(Long id)
//
    List<Customer> listAllByTypeOrName(CustomerType customerType, String cName)
}