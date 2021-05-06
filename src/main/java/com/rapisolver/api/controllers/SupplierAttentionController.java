package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.CreateSupplierAttentionDTO;
import com.rapisolver.api.dtos.SupplierAttentionDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.AttentionService;
import com.rapisolver.api.services.SupplierAttentionService;
import com.rapisolver.api.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/rapiSolver/"+"/v1")
public class SupplierAttentionController {
    @Autowired
    SupplierAttentionService supplierAttentionService;

    @Autowired
    AttentionService attentionService;

    @Autowired
    SupplierService supplierService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/supplierattention")
    public RapisolverResponse<SupplierAttentionDTO> CreateSupplierAttention(@RequestBody @Valid CreateSupplierAttentionDTO createSupplierAttentionDTO)throws RapisolverException{
        return new RapisolverResponse<>(200,String.valueOf(HttpStatus.OK),"OK",supplierAttentionService.CreateSupplierAttention(createSupplierAttentionDTO));
    }
}
