package com.rapisolver.api.entities;

import com.rapisolver.api.enums.StatusOrder;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierAttention_id", nullable = false)
    private SupplierAttentions supplierAttention;

    @Column(nullable = false)
    private Date dateRequested;

    @Column(length = 100, nullable = false)
    private String comment;

    @Column
    private StatusOrder status;
}
