package com.rapisolver.api.repositories;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.CreateAttentionDTO;
import com.rapisolver.api.entities.Attention;
import com.rapisolver.api.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttentionRepository extends JpaRepository<Attention,Long> {


}
