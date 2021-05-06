package com.rapisolver.api.repositories;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.CreateAttentionDTO;
import com.rapisolver.api.entities.Attention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttentionRepository extends JpaRepository<Attention,Long> {
    List<Attention> findByNameContaining(String name);
}
