package com.rapisolver.api.exceptions;

public class UnauthorizedException extends RapisolverException {

    public UnauthorizedException(String message) {
        super(401, "UNAUTHORIZED", message);
    }
}
