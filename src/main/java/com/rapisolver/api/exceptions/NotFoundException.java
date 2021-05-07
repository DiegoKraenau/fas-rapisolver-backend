package com.rapisolver.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotFoundException extends RapisolverException {

    public NotFoundException(String message) {
        super(404, "NOT_FOUND", message);
    }
}
