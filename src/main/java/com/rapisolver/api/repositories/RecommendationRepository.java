package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findBySupplierAttentionId(Long supplierAttentionId);
}
