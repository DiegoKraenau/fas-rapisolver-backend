package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class CreateAttentionDTO {
    private String name;
    private String detail;
}
