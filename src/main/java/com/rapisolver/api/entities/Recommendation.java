package com.rapisolver.api.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "recommendations")
@Data
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer mark;

    @Column(length = 100)
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_attention_id", nullable = false)
    private SupplierAttention supplierAttention;
}
