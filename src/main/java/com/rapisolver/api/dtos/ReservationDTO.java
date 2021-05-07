package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReservationDTO {

    private Long id;

    private String country;

    private String state;

    private String city;

    private String address;

    private Integer userId;

    private Integer supplierAttentionId;

    private Date dateRequested;

    private String comment;

    private  String  status;
}
