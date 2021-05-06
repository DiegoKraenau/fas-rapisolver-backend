package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CreateRecommendationDTO;
import com.rapisolver.api.dtos.RecomendationDTO;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface RecommendationService {

    RecomendationDTO create(CreateRecommendationDTO createRecommendationDTO) throws RapisolverException;
    List<RecomendationDTO> getAll() throws RapisolverException;
    List<RecomendationDTO> getBySupplierAttentionId(Long supplierAttentionId) throws RapisolverException;
}
