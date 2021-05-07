package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendationDTO {
    private Long id;
    private Integer mark;
    private String note;
    private UserDTO user;
    private SupplierAtenttionAttDTO supplierAtenttion;
}
