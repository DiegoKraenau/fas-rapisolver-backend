package com.rapisolver.api.services;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.SupplierDTO;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface SupplierAttentionService {
    List<SupplierDTO> findSuppliersByAttention(String attention) throws RapisolverException;
    List<AttentionDTO> findAttentionsBySuppliers(Long supplier_Id) throws RapisolverException;
}
