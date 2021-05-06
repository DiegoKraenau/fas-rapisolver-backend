package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.Attention;
import com.rapisolver.api.entities.Supplier;
import com.rapisolver.api.entities.SupplierAttention;
import com.rapisolver.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierAttentionRepository  extends JpaRepository<SupplierAttention,Long> {
    List<SupplierAttention> findByAttentionIn(List<Attention> attentions);
    List<SupplierAttention> findBySupplierIn(List<User> users);
}
