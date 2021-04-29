package com.rapisolver.api.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//Comment
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String firstname;

    @Column(length = 30, nullable = false)
    private String lastname;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 9, nullable = false)
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date birthdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Score> scores;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userReceiver")
    private List<Score> scoresReceived;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier")
    private List<UserAttention> userAttentions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Reservation> reservations;
}
