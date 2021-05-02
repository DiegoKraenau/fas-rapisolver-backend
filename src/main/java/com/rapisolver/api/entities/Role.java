package com.rapisolver.api.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    private String name;

    @Column(name = "can_publish", nullable = false)
    private boolean canPublish;

/*    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<User> users;*/
}
