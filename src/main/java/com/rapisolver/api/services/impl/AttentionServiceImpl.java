package com.rapisolver.api.services.impl;

import com.rapisolver.api.dtos.AttentionDTO;
import com.rapisolver.api.dtos.CreateAttentionDTO;
import com.rapisolver.api.dtos.CreateReservationDTO;
import com.rapisolver.api.dtos.ReservationDTO;
import com.rapisolver.api.entities.*;
import com.rapisolver.api.enums.StatusOrder;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.AttentionRepository;
import com.rapisolver.api.repositories.CategoryRepository;
import com.rapisolver.api.services.AttentionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttentionServiceImpl implements AttentionService {
    @Autowired
    AttentionRepository attentionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public static final ModelMapper modelMapper=new ModelMapper();

    @Override
    public List<AttentionDTO> findAttentions() throws RapisolverException {
        List<Attention> attentionList;
        try {
            attentionList = attentionRepository.findAll();
        }catch (Exception e){
            throw new InternalServerErrorException("Lista de atenciones no encontranda");
        }
        return attentionList.stream().map(attention -> modelMapper.map(attention,AttentionDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AttentionDTO findById(Long attentionId) throws RapisolverException {
        Attention attention = attentionRepository.findById(attentionId).orElseThrow(() -> new NotFoundException("Attencion con el id"+ attentionId +  "no encontrado"));
        return modelMapper.map(attention,AttentionDTO.class);
    }

    @Override
    public AttentionDTO createAttention(CreateAttentionDTO createAttentionDTO) throws RapisolverException {

        final Category category= categoryRepository.findById(createAttentionDTO.getCategoryId()).orElseThrow(()->new NotFoundException("Categoria no encontrada"));

        Attention attentionEntity;
        Attention attention = new Attention();
        attention.setDetail(createAttentionDTO.getDetail());
        attention.setName(createAttentionDTO.getName());
        attention.setCategory(category);
        try{
            attentionEntity = attentionRepository.save(attention);
        }catch (Exception e){
            throw new NotFoundException("No se pudo crear la atencion");
        }

        return modelMapper.map(attentionEntity,AttentionDTO.class);
    }

    @Override
    public AttentionDTO updateAttention(Long id, CreateAttentionDTO createAttentionDTO) throws RapisolverException {

        Attention attentionObj;

        Attention attention = attentionRepository.findById(id).orElseThrow(() -> new NotFoundException("Servicio a actualizar no encontrado"));

        Category category= categoryRepository.findById(createAttentionDTO.getCategoryId()).orElseThrow(()->new NotFoundException("Categoria no encontrada"));



        try {

            attention.setName(createAttentionDTO.getName());
            attention.setDetail(createAttentionDTO.getDetail());
            attention.setCategory(category);
            attentionObj=attentionRepository.save(attention);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error!! No se pudo actualizar los servicios");
        }

        return modelMapper.map(attentionObj,AttentionDTO.class);
    }
}
