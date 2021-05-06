package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.SupplierAttention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierAttentionRepository  extends JpaRepository<SupplierAttention,Long> {
}
