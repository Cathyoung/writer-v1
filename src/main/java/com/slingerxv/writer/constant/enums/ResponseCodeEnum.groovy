package com.slingerxv.writer.constant.enums

import groovy.transform.CompileStatic

@CompileStatic
enum ResponseCodeEnum {

    SUCCESS('Success'),
    ERROR('Error happened'),
    NOT_FOUND('Not found'),
    API_ACCESS_DENIED('Access denied'),
    NO_AUTHORIZATION('You are not authorized to access this page'),
    INVALID_INPUT('Bad request: Invalid inputs'),
    TOO_MANY_REQUESTS('Too many requests'),
    LOGIN_FAILED('The password you’ve entered is incorrect'),
    USER_NOT_FOUND('User not found'),
    USER_DISABLED('User is disabled'),
    PARAMETER_ERROR("Parameter error, please check your request whether has illegal parameters"),
    USER_ALREADY_EXISTS('User already exists'),
    MAX_FILE_SISE('File size greater than specified'),
    FILE_NOT_FIND('File not find')

    String message

    private ResponseCodeEnum(String message) {
        this.message = message
    }

    String getCode() {
        name()
    }
}
