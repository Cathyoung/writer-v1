package com.slingerxv.writer.dao.mapper

import com.slingerxv.writer.dao.model.Magazine
import groovy.transform.CompileStatic
import org.apache.ibatis.annotations.Param

/**
 * Created by chenyong on 2018/9/1
 */
@CompileStatic
interface MagazineMapper {
    void insert(Magazine magazine)

    Magazine getById(@Param("id") Long id)

    void update(Magazine magazine)

    void delete(Magazine magazine)

    List<Magazine> listAllByTitle(@Param("title") String title,
                                  @Param("createStartTime") Long createStartTime,
                                  @Param("createEndTime") Long createEndTime)
}
