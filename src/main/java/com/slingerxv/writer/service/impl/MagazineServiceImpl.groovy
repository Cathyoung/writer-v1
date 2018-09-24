package com.slingerxv.writer.service.impl

import com.slingerxv.writer.constant.enums.DataRecoradStatus
import com.slingerxv.writer.dao.mapper.MagazineMapper
import com.slingerxv.writer.dao.model.Magazine
import com.slingerxv.writer.security.authorization.util.ContextHolder
import com.slingerxv.writer.service.MagazineService
import com.slingerxv.writer.utils.TimeUtils
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by chenyong on 2018/9/1
 */
@Service
@CompileStatic
class MagazineServiceImpl implements MagazineService {

    @Autowired
    MagazineMapper magazineMapper

    @Override
    void insert(String title, String content) {
        magazineMapper.insert(
                new Magazine(
                        title: title,
                        content: content,
                        creator: ContextHolder.currentUser.realName,
                        createTime: System.currentTimeMillis(),
                        enable: DataRecoradStatus.ENABLE.value as byte
                )
        )
    }

    @Override
    void update(Long id, String title, String content) {
        Magazine magazine = magazineMapper.getById(id)
        if (magazine) {
            magazine.title = title ?: magazine.title
            magazine.content = content ?: magazine.content
            magazine.updateTime = System.currentTimeMillis()
            magazine.updator = ContextHolder.currentUser.realName
            magazineMapper.update(magazine)
        }
    }

    @Override
    void delete(Long id) {
        magazineMapper.delete(new Magazine(
                id: id,
                updator: ContextHolder.currentUser.realName,
                updateTime: System.currentTimeMillis()
        ))
    }

    @Override
    List<Magazine> listAllByTitle(String title, String createStartTime, String createEndTime) {
        return magazineMapper.listAllByTitle(title,
                createStartTime ? TimeUtils.convertyyyyMMddToMills(createStartTime) : null,
                createEndTime ? TimeUtils.convertyyyyMMddToMills(createEndTime) : null)
    }
}
