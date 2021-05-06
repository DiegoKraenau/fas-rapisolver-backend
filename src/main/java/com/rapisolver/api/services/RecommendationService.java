package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CreateRecommendationDTO;
import com.rapisolver.api.dtos.RecommendationDTO;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface RecommendationService {

    RecommendationDTO create(CreateRecommendationDTO createRecommendationDTO) throws RapisolverException;
    List<RecommendationDTO> getAll() throws RapisolverException;
    List<RecommendationDTO> getBySupplierAttentionId(Long supplierAttentionId) throws RapisolverException;
}
