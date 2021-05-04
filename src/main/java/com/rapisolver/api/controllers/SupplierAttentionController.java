package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.SupplierDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.SupplierAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/rapiSolver/"+"/v1")

public class SupplierAttentionController {
    @Autowired
    SupplierAttentionService supplierAttentionService;

    @GetMapping("/suppliers-name/{name}")
    private RapisolverResponse<List<SupplierDTO>> getByName(@PathVariable @Valid String name) {
        List<SupplierDTO> supplier;
        try {
            supplier = supplierAttentionService.findSuppliersByAttention(name);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
        return new RapisolverResponse<>(200, "OK","Suppliers encontrados", supplier);
    }

}
