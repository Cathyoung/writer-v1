package com.slingerxv.writer.utils

import groovy.transform.CompileStatic

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@CompileStatic
class TimeUtils {

    static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd")
    static final DateTimeFormatter secFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")
    static final DateTimeFormatter chinaFormat = DateTimeFormatter.ofPattern("yyyy年MM月dd日")
    static final DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("yyyy-MM")
    static final ZoneOffset zoneOffset = ZoneOffset.of("+8")

    static Long convertyyyyMMddToMills(String date) {
        if (!date) {
            return null
        }
        return LocalDateTime.from(LocalDate.parse(date, format).atStartOfDay()).toInstant(zoneOffset).toEpochMilli()
    }

    static Integer calculateOverdueDaysByOverdueDate(String date) {
        LocalDate d = LocalDate.parse(date, format)
        LocalDate now = LocalDate.now()
        return ChronoUnit.DAYS.between(d, now)
    }

    static Integer calculateDiffDays(LocalDate date1, LocalDate date2) {
        return ChronoUnit.DAYS.between(date1, date2)
    }

    static LocalDate convertTimestampToLocalDate(Long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp)
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        return localDateTime.toLocalDate()
    }

    static LocalDate convertyyyyMMddToLocalDate(String date) {
        return LocalDate.parse(date, format)
    }

    static String convertLocalDateToMonthString(LocalDate date) {
        return date.format(monthFormat)
    }

    static String convertLocatDateToDateString(LocalDate date) {
        return date.format(format)
    }

    static String convertLocatDateToSecDateString(LocalDateTime date) {
        return date.format(secFormat)
    }

    static String convertLocatDateToChinaDateString(LocalDate date) {
        return date.format(chinaFormat)
    }

    static long getMorningMillisecondByTimeOffset(long offset) {
        LocalDate localDate = LocalDate.now()
        localDate = localDate.plusDays(offset)
        return LocalDateTime.from(localDate.atStartOfDay()).toInstant(zoneOffset).toEpochMilli()
    }
}
