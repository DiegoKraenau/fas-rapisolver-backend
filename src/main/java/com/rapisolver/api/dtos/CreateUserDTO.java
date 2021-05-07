package com.rapisolver.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateUserDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private Date birthdate;

}
