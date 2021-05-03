package com.rapisolver.api.services.impl;

import com.rapisolver.api.dtos.AttentionDTO;
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
    public List<SupplierDTO> findSuppliersByAttention(String attention) throws RapisolverException {
        Attention attention1=new Attention();
        attention1= attentionRepository.findByName(attention).orElseThrow(()->new NotFoundException("ATTENTION_NOT_FOUND"));
        List<SupplierAttentions> supplierAttentions;
        supplierAttentions=supplierAttentionRepository.findByAttention_Id(attention1.getId());
        List<Supplier> suppliers;
        suppliers=supplierRepository.findBySupplierAttentionsListIn(supplierAttentions);
        return suppliers.stream().map(supplier -> modelMapper.map(supplier, SupplierDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<AttentionDTO> findAttentionsBySuppliers(Long supplier_Id) throws RapisolverException {
        Supplier supplier1 = new Supplier();
        supplier1=supplierRepository.findById(supplier_Id).orElseThrow(()->new NotFoundException("SUPPLIER_NOT_FOUND"));
        List<SupplierAttentions> supplierAttentions;
        supplierAttentions = supplierAttentionRepository.findBySupplierId(supplier1.getId());
        List<Attention> attentions;
        attentions = attentionRepository.findBySupplierAttentionsIn(supplierAttentions);
        return attentions.stream().map(attention -> modelMapper.map(attention,AttentionDTO.class)).collect(Collectors.toList());
    }
}
