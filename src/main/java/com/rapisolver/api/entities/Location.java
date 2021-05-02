package com.rapisolver.api.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "locations")
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String country;

    @Column(length = 20, nullable = false)
    private String state;

    @Column(length = 20, nullable = false)
    private String city;

    @Column(length = 70, nullable = false)
    private String address;

  /*  @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    private List<Reservation> reservations;*/
}
