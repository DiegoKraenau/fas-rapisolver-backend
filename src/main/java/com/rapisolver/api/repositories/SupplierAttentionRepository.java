package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.SupplierAttention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierAttentionRepository  extends JpaRepository<SupplierAttention,Long> {
    List<SupplierAttention> findByAttention_Id(Long attention_id);
    List<SupplierAttention> findBySupplierId(Long supplier_id);
}
