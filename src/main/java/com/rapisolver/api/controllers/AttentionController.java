package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.*;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.AttentionService;
import java.util.List;
import javax.validation.Valid;
import com.rapisolver.api.services.SupplierAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rapiSolver/" + "/v1")
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
      return new RapisolverResponse<>(
        e.getCode(),
        e.getStatus(),
        e.getMessage()
      );
    }
    return new RapisolverResponse<>(
      200,
      "OK",
      "Atencion encontrada",
      attention
    );
  }

    @GetMapping("/atenttions-name/{names}")
    private RapisolverResponse<List<SupplierAtenttionAttDTO>> getByName(@PathVariable @Valid String names) {
        List<SupplierAtenttionAttDTO> attention;
        try {
            attention = supplierAttentionService.findSuppliersByAttention(names);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
        return new RapisolverResponse<>(200, "OK","Atenciones encontradas", attention);
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

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/attention/status")
  public RapisolverResponse<AttentionDTO> getAllAttentionByStatus()
    throws RapisolverException {
    AttentionDTO attentionDTO = (AttentionDTO) attentionService.getAllAttentionByDelivered();
    return new RapisolverResponse<>(
      200,
      "OK",
      "Lista de servicios más pedidos segun su estatus",
      attentionDTO
    );
  }
}
