package com.rapisolver.api.controller.commons;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@JsonPropertyOrder
public class RapiSolverResponse implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer code;

    private String message;

    private Object data;

    @Builder.Default
    private Date date = new Date();
}
