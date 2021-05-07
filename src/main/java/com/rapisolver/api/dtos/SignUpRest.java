package com.rapisolver.api.dtos;

import com.rapisolver.api.entities.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class SignUpRest {

    @Column(length = 100, nullable = false)
    private String firstname;

    @Column(length = 100, nullable = false)
    private String lastname;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 250, nullable = false)
    private String password;

    @Column(length = 9, nullable = false)
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date birthdate;

    @Column(length = 20, nullable = false)
    private String country;

    @Column(length = 20, nullable = false)
    private String state;

    @Column(length = 20, nullable = false)
    private String city;

    @Column(length = 70, nullable = false)
    private String address;

}
