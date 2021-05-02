package com.rapisolver.api.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users_attentions")
@Data
public class SupplierAttentions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double price;

/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_supplier_id", nullable = false)
    private User supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attention_id", nullable = false)
    private Attention attention;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userAttention")
    private List<Reservation> reservations;*/
}
