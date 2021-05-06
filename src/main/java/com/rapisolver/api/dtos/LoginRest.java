package com.rapisolver.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class LoginRest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public LoginRest() {

    }
}
