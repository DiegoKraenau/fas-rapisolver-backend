package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateReservationDTO {

    private String country;

    private String state;

    private String city;

    private String address;

    private Long userId;

    private Long supplierAttentionId;

    private Date dateRequested;

    private String comment;
}
