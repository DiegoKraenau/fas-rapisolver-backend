package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.*;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.AttentionService;
import com.rapisolver.api.services.SupplierAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/rapiSolver/"+"/v1")
public class AttentionController {
    @Autowired
    AttentionService attentionService;

    @Autowired
    SupplierAttentionService supplierAttentionService;

    @GetMapping("/attentions")
    private RapisolverResponse<List<AttentionDTO>> getAll() {
        List<AttentionDTO> attentionList;
        try {
            attentionList = attentionService.findAttentions();
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
        return new RapisolverResponse<>(200, "OK","Lista de atenciones", attentionList);
    }

    @GetMapping("/attention/{attentionId}")
    private RapisolverResponse<AttentionDTO> getByAttentionId(@PathVariable Long attentionId) {
        AttentionDTO attention;
        try {
            attention = attentionService.findById(attentionId);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
        return new RapisolverResponse<>(200, "OK","Atencion encontrada", attention);
    }

    @GetMapping("/attention-names/{names}")
    private RapisolverResponse<List<SupplierAtenttionAttDTO>> getBySupplierName(@PathVariable @Valid  String names) {
        List<SupplierAtenttionAttDTO> attentions;
        try {
            attentions = supplierAttentionService.findAttentionsBySuppliers(names);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
        return new RapisolverResponse<>(200, "OK","Atencion encontrada", attentions);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/attention")
    public RapisolverResponse<AttentionDTO> createAttention(@RequestBody @Valid CreateAttentionDTO createAttentionDTO) throws RapisolverException{
        return new RapisolverResponse<>(200,String.valueOf(HttpStatus.OK),"OK",attentionService.createAttention(createAttentionDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/attention/{attentionId}")
    public RapisolverResponse<AttentionDTO> UpdateAttention(@PathVariable Long attentionId,@RequestBody @Valid CreateAttentionDTO createAttentionDTO){
        AttentionDTO attentionDTO;
        try {
            attentionDTO=attentionService.updateAttention(attentionId,createAttentionDTO);
        }catch (RapisolverException ex){
            return new RapisolverResponse<>(ex.getCode(),ex.getStatus(),ex.getMessage());
        }
        return new RapisolverResponse<>(200,"ok","Servicio actualizado correctamente",attentionDTO);
    }
}
