package com.rapisolver.api.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @Column(length = 50)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Attention> attentions;
}
