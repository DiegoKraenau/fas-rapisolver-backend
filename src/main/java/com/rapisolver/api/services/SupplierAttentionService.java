package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CreateAttentionDTO;
import com.rapisolver.api.dtos.CreateSupplierAttentionDTO;
import com.rapisolver.api.dtos.SupplierAttentionDTO;
import com.rapisolver.api.exceptions.RapisolverException;

public interface SupplierAttentionService {
    SupplierAttentionDTO CreateSupplierAttention(CreateSupplierAttentionDTO createSupplierAttentionDTO)throws RapisolverException;
}
