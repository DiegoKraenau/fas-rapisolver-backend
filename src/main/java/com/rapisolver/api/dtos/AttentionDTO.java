package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttentionDTO {
    private Long categoryId;
    private String name;
    private String detail;
    private CategoryDTO categoryDTO;
}
