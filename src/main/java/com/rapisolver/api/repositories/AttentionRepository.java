package com.rapisolver.api.repositories;

import com.rapisolver.api.entities.Attention;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttentionRepository extends JpaRepository<Attention, Long> {
  @Query(
    value = "SELECT A.NAME" +
    "FROM ATTENTIONS A INNER JOIN SUPPLIER_ATTENTIONS B ON A.id = B.attention_id INNER JOIN RESERVATIONS C ON B.id = C.supplier_attention_id WHERE C.STATUS = 2 GROUP BY A.NAME",
    nativeQuery = true
  )
  List<Attention> getAllAttentionsByDelivered();
  List<Attention> findByNameContaining(String name);
}
// ,COUNT(*) AS CANTIDAD
