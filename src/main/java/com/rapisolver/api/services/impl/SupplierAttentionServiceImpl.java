package com.rapisolver.api.services.impl;

import com.rapisolver.api.dtos.CreateAttentionDTO;
import com.rapisolver.api.dtos.CreateSupplierAttentionDTO;
import com.rapisolver.api.dtos.SupplierAttentionDTO;
import com.rapisolver.api.entities.Attention;
import com.rapisolver.api.entities.Category;
import com.rapisolver.api.entities.Supplier;
import com.rapisolver.api.entities.SupplierAttention;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.AttentionRepository;
import com.rapisolver.api.repositories.CategoryRepository;
import com.rapisolver.api.repositories.SupplierAttentionRepository;
import com.rapisolver.api.repositories.SupplierRepository;
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

    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<SupplierAtenttionSupDTO> findSuppliersByAttention(String attention) throws RapisolverException {
        Attention attention1=new Attention();
        attention1= attentionRepository.findByName(attention).orElseThrow(()->new NotFoundException("ATTENTION_NOT_FOUND"));
        List<SupplierAttention> supplierAttentions;
        supplierAttentions=supplierAttentionRepository.findByAttention_Id(attention1.getId());
        return supplierAttentions.stream().map(supplierAttentions1 -> modelMapper.map(supplierAttentions1, SupplierAtenttionSupDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<SupplierAtenttionAttDTO> findAttentionsBySuppliers(String comercialName) throws RapisolverException {
        Supplier supplier1 = new Supplier();
        supplier1=supplierRepository.findByComercialName(comercialName).orElseThrow(()->new NotFoundException("SUPPLIER_NOT_FOUND"));
        List<SupplierAttention> supplierAttentions;
        supplierAttentions = supplierAttentionRepository.findBySupplierId(supplier1.getId());
        return supplierAttentions.stream().map(supplierAttentions1 -> modelMapper.map(supplierAttentions1,SupplierAtenttionAttDTO.class)).collect(Collectors.toList());

    @Autowired
    SupplierAttentionRepository supplierAttentionRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    AttentionRepository attentionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public static final ModelMapper modelMapper=new ModelMapper();


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
