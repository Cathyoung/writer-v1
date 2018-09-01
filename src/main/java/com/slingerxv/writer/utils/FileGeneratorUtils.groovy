package com.slingerxv.writer.utils

import groovy.transform.CompileStatic

@CompileStatic
class FileGeneratorUtils {

    static Random random = new Random()

    static String fileGenerateId() {
        return String.format(Locale.US, "%d%04d", System.currentTimeMillis(), Math.abs(random.nextInt()))
    }
}
