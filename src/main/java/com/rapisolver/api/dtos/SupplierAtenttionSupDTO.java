package com.rapisolver.api.dtos;


import com.rapisolver.api.entities.Supplier;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class SupplierAtenttionSupDTO {

    private Long id;

    private double price;

    private SupplierDTO supplier;


}
