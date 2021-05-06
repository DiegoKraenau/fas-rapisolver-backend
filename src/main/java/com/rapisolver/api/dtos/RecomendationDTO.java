package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecomendationDTO {
    private Long id;
    private Integer mark;
    private String note;
    private UserDTO user;
    private SupplierAttentionDTO supplierAttention;
}
