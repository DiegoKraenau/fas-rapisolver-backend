package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private Date birthdate;
    private RoleDTO role;

}
