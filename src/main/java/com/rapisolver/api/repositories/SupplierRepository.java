package com.rapisolver.api.repositories;


import com.rapisolver.api.entities.Supplier;
import com.rapisolver.api.entities.SupplierAttentions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository  extends JpaRepository<Supplier,Long> {
    List<Supplier> findBySupplierAttentionsListIn(List<SupplierAttentions> supplierAttentions);

}
