package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.*;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/rapiSolver/"+"/v1")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservations")
    private RapisolverResponse<List<ReservationDTO>> getAll() {
        List<ReservationDTO> reservationDTOS;
        try {
            reservationDTOS = reservationService.findAll();
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
        return new RapisolverResponse<>(200, "OK","Lista de reservas", reservationDTOS);
    }

    @GetMapping("/reservation/{reservationId}")
    private RapisolverResponse<ReservationDTO> getByReservationId(@PathVariable Long reservationId) {
        ReservationDTO reservationDTO;
        try {
            reservationDTO = reservationService.findById(reservationId);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }
        return new RapisolverResponse<>(200, "OK","Reservacion encontrada", reservationDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/createReservation")
    public RapisolverResponse<ReservationDTO> createReservation(@RequestBody @Valid CreateReservationDTO createReservationDTO) throws RapisolverException{
        return new RapisolverResponse<>(200,String.valueOf(HttpStatus.OK),"OK",reservationService.createReservation(createReservationDTO));
    }

    @PutMapping("/reservation/{reservationId}")
    private RapisolverResponse<ReservationDTO> updateReservationById(@PathVariable Long reservationId, @RequestBody @Valid UpdateReservationDTO updateReservationDTO) {
        ReservationDTO reservationDTO;
        try {
            reservationDTO = reservationService.updateReservation(reservationId, updateReservationDTO);
        } catch (RapisolverException e) {
            return new RapisolverResponse<>(e.getCode(), e.getStatus(), e.getMessage());
        }

        return new RapisolverResponse<>(200, "OK","Reservacion actualizada correctamente", reservationDTO);
    }

}
