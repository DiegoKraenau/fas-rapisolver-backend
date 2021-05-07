package com.rapisolver.api.services;

import com.rapisolver.api.dtos.*;

import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface SupplierAttentionService {
    List<SupplierAtenttionAttDTO> findSuppliersByAttention(String attention) throws RapisolverException;

    List<SupplierAtenttionSupDTO> findAttentionsBySuppliers(String names) throws RapisolverException;

    List<SupplierAtenttionAttDTO> findAttentionsReserved(Long userId) throws RapisolverException;

    SupplierAttentionDTO CreateSupplierAttention(CreateSupplierAttentionDTO createSupplierAttentionDTO) throws RapisolverException;
}
