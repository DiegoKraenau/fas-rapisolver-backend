package com.rapisolver.api.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "recommendations")
@Data
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_attention_id", nullable = false)
    private SupplierAttentions supplierAttentions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "comment",length = 100, nullable = false)
    private String comment;
}