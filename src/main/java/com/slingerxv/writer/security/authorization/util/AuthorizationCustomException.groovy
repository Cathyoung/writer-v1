package com.slingerxv.writer.security.authorization.util

import groovy.transform.CompileStatic

/**
 * @project audit-backend
 * @author frank on 11/13/17.
 */
@CompileStatic
class AuthorizationCustomException extends RuntimeException {
    AuthorizationCustomException() {
    }

    AuthorizationCustomException(String message) {
        super(message)
    }

    AuthorizationCustomException(String message, Throwable cause) {
        super(message, cause)
    }

    AuthorizationCustomException(Throwable cause) {
        super(cause)
    }

    AuthorizationCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace)
    }
}
