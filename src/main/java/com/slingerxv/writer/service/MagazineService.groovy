package com.slingerxv.writer.service

import com.slingerxv.writer.dao.model.Magazine

/**
 * Created by chenyong on 2018/9/2
 */
interface MagazineService {
    void insert(String title, String content)

    void update(Long id, String title, String content)

    void delete(Long id)

    List<Magazine> listAllByTitle(String title, String createStartTime, String createEndTime)
}