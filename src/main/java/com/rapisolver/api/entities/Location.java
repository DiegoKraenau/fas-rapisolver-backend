package com.rapisolver.api.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
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

    @OneToOne(mappedBy = "location",cascade = CascadeType.ALL)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    private List<Reservation> reservations;

    public Location(String country, String state, String city, String address) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
    }
}
