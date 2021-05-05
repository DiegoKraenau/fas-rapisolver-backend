package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierAttentionDTO {
    private double price;

    private Long supplierId;

    private AttentionDTO attention;
}
