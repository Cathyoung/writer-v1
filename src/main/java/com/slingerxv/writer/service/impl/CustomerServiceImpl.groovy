package com.slingerxv.writer.service.impl

import com.slingerxv.writer.constant.enums.CustomerType
import com.slingerxv.writer.dao.mapper.CustomerMapper
import com.slingerxv.writer.dao.model.Customer
import com.slingerxv.writer.service.CustomerService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tk.mybatis.mapper.entity.Example

/**
 * Created by chenyong on 2018/9/1
 */
@Service
@CompileStatic
class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper

    @Override
    void insert(Customer customer) {
        customerMapper.insert(customer)
    }

    @Override
    List<Customer> listAllByTypeOrName(CustomerType customerType, String cName) {
        Example example = new Example(Customer.class);
        Example.Criteria criteria = example.createCriteria();
        customerType ? criteria.andEqualTo("customerType", customerType.name()) : null
        cName ? criteria.andLike("cName", cName +"%") : null
        return customerMapper.selectByExample(example)
    }
}
