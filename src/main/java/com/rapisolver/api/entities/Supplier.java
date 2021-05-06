package com.rapisolver.api.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
//@Table(name="supplier")
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("SUPPLIER")
public class Supplier extends User {
    @Column(length = 100)
    String comercialName;

    public Supplier(String firstName, String lastName, String email, String password, String phone, Date birthdate, Role role, String comercialName) {
        super(firstName, lastName, email, password, phone, birthdate, role);
        this.comercialName = comercialName;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "supplier")
    private List<SupplierAttentions> supplierAttentions;

}
