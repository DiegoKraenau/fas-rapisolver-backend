package com.rapisolver.api.services;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.SupplierAtenttionAttDTO;
import com.rapisolver.api.dtos.SupplierAtenttionSupDTO;

import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface SupplierAttentionService {
    List<SupplierAtenttionSupDTO> findSuppliersByAttention(String attention) throws RapisolverException;
    List<SupplierAtenttionAttDTO> findAttentionsBySuppliers(String comercialName) throws RapisolverException;
}
