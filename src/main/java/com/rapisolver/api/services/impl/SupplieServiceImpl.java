package com.rapisolver.api.services.impl;

import com.rapisolver.api.dtos.SupplierDTO;
import com.rapisolver.api.entities.Supplier;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.SupplierRepository;
import com.rapisolver.api.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class SupplieServiceImpl  implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public static final ModelMapper modelMapper=new ModelMapper();

    @Override
    public SupplierDTO findById(Long id) throws RapisolverException {
        Supplier supplier =supplierRepository.findById(id).orElseThrow(()->new NotFoundException("Proveedor No encontrado"));
        return modelMapper.map(supplier,SupplierDTO.class);
    }
}
