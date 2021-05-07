package com.rapisolver.api.exceptions;

import lombok.Getter;

@Getter
public class RapisolverException extends Exception {

    private static final long serialVersionUID = 1L;

    private final int code;
    private final String status;

    public RapisolverException(int code, String status, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
