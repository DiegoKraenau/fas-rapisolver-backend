package com.rapisolver.api.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//Comment
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 250, nullable = false)
    private String password;

    @Column(length = 9, nullable = false)
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date birthdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User(String firstName, String lastName, String email, String password, String phone, Date birthdate, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birthdate = birthdate;
        this.role = role;
    }
/*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Score> scores;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userReceiver")
    private List<Score> scoresReceived;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier")
    private List<UserAttention> userAttentions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Reservation> reservations;*/
}
