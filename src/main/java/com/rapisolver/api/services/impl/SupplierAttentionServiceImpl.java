package com.rapisolver.api.services.impl;

import com.rapisolver.api.dtos.*;
import com.rapisolver.api.entities.*;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.*;
import com.rapisolver.api.services.SupplierAttentionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<SupplierAtenttionAttDTO> findSuppliersByAttention(String attention) throws RapisolverException {
        List<Attention> attention1;
        attention1= attentionRepository.findByNameContaining(attention);
        List<SupplierAttention> supplierAttentions;
        supplierAttentions=supplierAttentionRepository.findByAttentionIn(attention1);

        return supplierAttentions.stream().map(supplierAttention -> modelMapper.map(supplierAttention, SupplierAtenttionAttDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<SupplierAtenttionSupDTO> findAttentionsBySuppliers(String supplier) throws RapisolverException {
        List<User> supplier1;
        supplier1 = userRepository.findByFirstNameContaining(supplier);
        List<SupplierAttention> supplierAttentions;
        supplierAttentions = supplierAttentionRepository.findBySupplierIn(supplier1);

        return supplierAttentions.stream().map(supplierAttention -> modelMapper.map(supplierAttention, SupplierAtenttionSupDTO.class)).collect(Collectors.toList());
    }

    //Long supplierId, CreateAttentionDTO attention, double price
    @Override
    public SupplierAttentionDTO CreateSupplierAttention(CreateSupplierAttentionDTO createSupplierAttentionDTO) throws RapisolverException {

        CreateAttentionDTO attentionInput=createSupplierAttentionDTO.getAttention();

        Supplier supplier=supplierRepository.findById(createSupplierAttentionDTO.getSupplierId()).orElseThrow(()->new NotFoundException("Proveedor no registrado"));

        Category category= categoryRepository.findById(attentionInput.getCategoryId()).orElseThrow(()->new NotFoundException("Categoria no encontrada"));

        Attention attentionparam;
        Attention attentionObj=new Attention();

        try{
            attentionObj.setName(attentionInput.getName());
            attentionObj.setDetail(attentionInput.getDetail());
            attentionObj.setCategory(category);
            attentionparam=attentionRepository.save(attentionObj);
        }catch(Exception ex){
            throw new InternalServerErrorException("Error!! No se pudo crear el servicio");
        }

        SupplierAttention supplierAttentionsObj;
        SupplierAttention supplierAttentions=new SupplierAttention();
        try{
            supplierAttentions.setSupplier(supplier);
            supplierAttentions.setAttention(attentionparam);
            supplierAttentions.setPrice(createSupplierAttentionDTO.getPrice());
            supplierAttentionsObj=supplierAttentionRepository.save(supplierAttentions);
        }catch(Exception ex){
            throw new InternalServerErrorException("Error!! No sse pudo crear la relacion Servicio/Proveedor");
        }
        return modelMapper.map(supplierAttentionsObj,SupplierAttentionDTO.class);
    }
}
