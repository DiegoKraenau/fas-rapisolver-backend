package com.rapisolver.api.services;

import com.rapisolver.api.dtos.SupplierDTO;
import com.rapisolver.api.exceptions.RapisolverException;

public interface SupplierService {
    SupplierDTO findById(Long id)throws RapisolverException;
}
