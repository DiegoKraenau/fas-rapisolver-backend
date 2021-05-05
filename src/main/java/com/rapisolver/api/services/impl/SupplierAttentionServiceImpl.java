package com.rapisolver.api.services.impl;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.SupplierAtenttionAttDTO;
import com.rapisolver.api.dtos.SupplierAtenttionSupDTO;
import com.rapisolver.api.dtos.SupplierDTO;
import com.rapisolver.api.entities.Attention;
import com.rapisolver.api.entities.Supplier;
import com.rapisolver.api.entities.SupplierAttentions;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.AttentionRepository;
import com.rapisolver.api.repositories.SupplierAttentionRepository;
import com.rapisolver.api.repositories.SupplierRepository;
import com.rapisolver.api.services.SupplierAttentionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierAttentionServiceImpl  implements  SupplierAttentionService {
    @Autowired
    private AttentionRepository attentionRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierAttentionRepository supplierAttentionRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<SupplierAtenttionSupDTO> findSuppliersByAttention(String attention) throws RapisolverException {
        Attention attention1=new Attention();
        attention1= attentionRepository.findByName(attention).orElseThrow(()->new NotFoundException("ATTENTION_NOT_FOUND"));
        List<SupplierAttentions> supplierAttentions;
        supplierAttentions=supplierAttentionRepository.findByAttention_Id(attention1.getId());
        return supplierAttentions.stream().map(supplierAttentions1 -> modelMapper.map(supplierAttentions1, SupplierAtenttionSupDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<SupplierAtenttionAttDTO> findAttentionsBySuppliers(String comercialName) throws RapisolverException {
        Supplier supplier1 = new Supplier();
        supplier1=supplierRepository.findByComercialName(comercialName).orElseThrow(()->new NotFoundException("SUPPLIER_NOT_FOUND"));
        List<SupplierAttentions> supplierAttentions;
        supplierAttentions = supplierAttentionRepository.findBySupplierId(supplier1.getId());
        return supplierAttentions.stream().map(supplierAttentions1 -> modelMapper.map(supplierAttentions1,SupplierAtenttionAttDTO.class)).collect(Collectors.toList());
    }
}
