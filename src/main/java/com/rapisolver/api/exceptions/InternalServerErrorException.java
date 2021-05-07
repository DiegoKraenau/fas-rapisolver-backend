package com.rapisolver.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InternalServerErrorException extends RapisolverException {

    public InternalServerErrorException(String message) {
        super(500, "INTERNAL_SERVER_ERROR", message);
    }
}
