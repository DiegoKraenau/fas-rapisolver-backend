package com.rapisolver.api.services.impl;

import com.rapisolver.api.dtos.CreateRecommendationDTO;
import com.rapisolver.api.dtos.RecommendationDTO;
import com.rapisolver.api.entities.Recommendation;
import com.rapisolver.api.entities.SupplierAttention;
import com.rapisolver.api.entities.User;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.RecommendationRepository;
import com.rapisolver.api.repositories.SupplierAttentionRepository;
import com.rapisolver.api.repositories.UserRepository;
import com.rapisolver.api.services.RecommendationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private static final ModelMapper MAPPER = new ModelMapper();

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private SupplierAttentionRepository supplierAttentionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RecommendationDTO create(CreateRecommendationDTO createRecommendationDTO) throws RapisolverException {

        User userDB = userRepository.findById(createRecommendationDTO.getUserId())
                        .orElseThrow(() -> new NotFoundException("USER_NOT_FOUND"));

        SupplierAttention supplierAttentionDB = supplierAttentionRepository.findById(createRecommendationDTO.getSupplierAttentionId())
                                                .orElseThrow(() -> new NotFoundException("SUPPLIER_ATTENTION_NOT_FOUND"));


        try {
            Recommendation recommendation = new Recommendation();
            recommendation.setMark(createRecommendationDTO.getMark());
            recommendation.setNote(createRecommendationDTO.getNote());
            recommendation.setUser(userDB);
            recommendation.setSupplierAttention(supplierAttentionDB);
            recommendation = recommendationRepository.save(recommendation);
            return MAPPER.map(recommendation, RecommendationDTO.class);
        } catch (Exception e) {
            throw new InternalServerErrorException("CREATE_RECOMMENDATION_ERROR");
        }
    }

    @Override
    public List<RecommendationDTO> getAll() throws RapisolverException {
        try {
            List<Recommendation> recommendations = recommendationRepository.findAll();
            return recommendations.stream().map(r -> MAPPER.map(r, RecommendationDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerErrorException("GET_RECOMMENDATIONS_ERROR");
        }
    }

    @Override
    public List<RecommendationDTO> getBySupplierAttentionId(Long supplierAttentionId) throws RapisolverException {
        try {
            List<Recommendation> recommendations = recommendationRepository.findBySupplierAttentionId(supplierAttentionId);
            return recommendations.stream().map(r -> MAPPER.map(r, RecommendationDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerErrorException("GET_RECOMMENDATIONS_BY_SUPPLIERATTENTION_ID_ERROR");
        }
    }
}
