package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.CreateRecommendationDTO;
import com.rapisolver.api.dtos.RecommendationDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rapiSolver/v1")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/recommendations")
    public RapisolverResponse<RecommendationDTO> create(@Valid @RequestBody CreateRecommendationDTO createRecommendationDTO) {
        try {
            RecommendationDTO recommendationDTO = recommendationService.create(createRecommendationDTO);
            return new RapisolverResponse<>(201,"CREATED", "Recomendacion creada", recommendationDTO);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/recommendations")
    public RapisolverResponse<List<RecommendationDTO>> getAll() {
        try {
            List<RecommendationDTO> recommendationDTOS = recommendationService.getAll();
            return new RapisolverResponse<>(200,"OK", "Lista de recomendaciones", recommendationDTOS);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
    }

    @GetMapping("/recommendations/supplier-attention/{id}")
    public RapisolverResponse<List<RecommendationDTO>> getBySupplierAttentionId(@PathVariable Long id) {
        try {
            List<RecommendationDTO> recommendationDTOS = recommendationService.getBySupplierAttentionId(id);
            return new RapisolverResponse<>(200,"OK", "Lista de recomendaciones del servicio con id:"+id, recommendationDTOS);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
    }

}
