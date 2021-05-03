package com.rapisolver.api.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="customer")
@NoArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends User {

    @Column(length = 100)
    String randomColumn;

    public Customer(String firstName, String lastName, String email, String password, String phone, Date birthdate, Role role, String randomColumn) {
        super(firstName, lastName, email, password, phone, birthdate, role);
        this.randomColumn = randomColumn;
    }

}
