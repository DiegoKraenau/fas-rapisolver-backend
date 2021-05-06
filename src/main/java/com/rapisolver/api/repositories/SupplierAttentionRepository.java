package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.SupplierAttention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierAttentionRepository  extends JpaRepository<SupplierAttentions,Long> {
    List<SupplierAttentions> findByAttention_Id(Long attention_id);
    List<SupplierAttentions> findBySupplierId(Long supplier_id);
}
