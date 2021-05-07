package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository  extends JpaRepository<Location,Long> {


}
