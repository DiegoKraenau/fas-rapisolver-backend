package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.Attention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttentionRepository extends JpaRepository<Attention,Long> {
}
