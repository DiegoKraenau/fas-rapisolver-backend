package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierAtenttionSupDTO {

    private Long id;
    private double price;
    private UserDTO supplier;
    private AttentionDTO attention;
}
