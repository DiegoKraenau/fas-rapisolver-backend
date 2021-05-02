package com.rapisolver.api.services;

import com.rapisolver.api.dtos.*;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface AttentionService {
    List<AttentionDTO> findAttentions() throws RapisolverException;
    AttentionDTO findById(Long attentionId) throws RapisolverException;
    AttentionDTO createAttention(CreateAttentionDTO createAttentionDTO) throws RapisolverException;
    AttentionDTO updateAttention(Long id, CreateReservationDTO createReservationDTO) throws RapisolverException;
}
