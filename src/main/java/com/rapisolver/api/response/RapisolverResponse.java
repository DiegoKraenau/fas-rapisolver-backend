package com.rapisolver.api.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RapisolverResponse<T> implements Serializable {

    private static final long serialVersionUID=1L;

    private int code;
    private String status;
    private String message;
    private T data;

    public RapisolverResponse(int code, String status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public RapisolverResponse(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
