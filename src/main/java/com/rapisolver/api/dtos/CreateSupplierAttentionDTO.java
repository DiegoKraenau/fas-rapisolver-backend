package com.rapisolver.api.dtos;


import com.rapisolver.api.entities.Attention;
import com.rapisolver.api.entities.Reservation;
import com.rapisolver.api.entities.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class CreateSupplierAttentionDTO {

    private double price;

    private Long supplierId;

    private CreateAttentionDTO attention;
}
