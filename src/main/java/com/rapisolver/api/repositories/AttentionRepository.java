package com.rapisolver.api.repositories;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.CreateAttentionDTO;
import com.rapisolver.api.entities.Attention;
import com.rapisolver.api.entities.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttentionRepository extends JpaRepository<Attention, Long> {
  @Query(
    value = "SELECT A.NAME" +
    "FROM ATTENTIONS A INNER JOIN SUPPLIER_ATTENTIONS B ON A.ID = B.ATTENTION_ID INNER JOIN RESERVATIONS C ON B.ID = B.USER_SUPPLIER_ID WHERE C.STATUS = 2 GROUP BY A.NAME",
    nativeQuery = true
  )
  List<Attention> getAllAttentionsByDelivered();
}
// ,COUNT(*) AS CANTIDAD
