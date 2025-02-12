package com.nagp.amcart.core.model.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class AmCartException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatusCode;
    private final Map<String, String> errorDetails;

    public AmCartException(String errorCode, String errorMessage, HttpStatus httpStatusCode
            , Map<String, String> errorDetails) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
        this.errorDetails = errorDetails;
    }

    public AmCartException(String errorCode, String errorMessage, HttpStatus httpStatusCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
        this.errorDetails = null;
    }
}
