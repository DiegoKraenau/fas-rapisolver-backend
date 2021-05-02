package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository  extends JpaRepository<Supplier,Long> {
}
