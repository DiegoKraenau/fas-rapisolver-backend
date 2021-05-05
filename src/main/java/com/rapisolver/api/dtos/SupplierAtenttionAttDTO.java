package com.rapisolver.api.dtos;

import com.rapisolver.api.entities.Attention;
import com.rapisolver.api.entities.Reservation;
import com.rapisolver.api.entities.Supplier;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class SupplierAtenttionAttDTO {

    private Long id;
    private double price;
    private AttentionDTO attention;
}
