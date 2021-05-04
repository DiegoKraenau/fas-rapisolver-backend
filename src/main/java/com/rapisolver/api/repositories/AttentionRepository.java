package com.rapisolver.api.repositories;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.CreateAttentionDTO;
import com.rapisolver.api.entities.Attention;
import com.rapisolver.api.entities.SupplierAttentions;
import com.rapisolver.api.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttentionRepository extends JpaRepository<Attention,Long> {
    Optional<Attention> findByName(String name);
    List<Attention> findBySupplierAttentionsIn(List<SupplierAttentions> supplierAttentions);


}
