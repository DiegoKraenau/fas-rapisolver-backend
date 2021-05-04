package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.Reservation;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReservationRepository
  extends JpaRepository<Reservation, Long> {
  @Modifying
  @Transactional
  void deleteById(Long id);
}
