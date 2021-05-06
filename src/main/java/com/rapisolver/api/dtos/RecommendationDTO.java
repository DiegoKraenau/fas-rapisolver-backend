package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RecommendationDTO {
    private Long id;
    private Integer supplierAttentionsId;
    private Integer userId;
    private Date date;
    private String comment;
}
