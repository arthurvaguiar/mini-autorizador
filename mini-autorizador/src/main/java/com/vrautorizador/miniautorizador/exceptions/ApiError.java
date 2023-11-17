package com.vrautorizador.miniautorizador.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public class ApiError {
    private String error;
    private String message;

    private HttpStatus status;

    public ApiError(String error, String message, HttpStatus status) {
        this.error = error;
        this.message = message;
        this.status = status;
    }

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
