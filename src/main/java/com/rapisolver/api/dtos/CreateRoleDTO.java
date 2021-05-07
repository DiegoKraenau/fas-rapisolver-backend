package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoleDTO {

    private String name;
    private boolean canPublish;

}
