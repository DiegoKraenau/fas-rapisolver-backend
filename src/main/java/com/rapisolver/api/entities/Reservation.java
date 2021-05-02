package com.rapisolver.api.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date datetime;

    @Column(nullable = false)
    private Integer status;

/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_attention_id", nullable = false)
    private SupplierAttention userAttention;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_customer_id")
    private User customer;*/
}
