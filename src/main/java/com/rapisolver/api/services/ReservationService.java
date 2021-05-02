package com.rapisolver.api.services;


import com.rapisolver.api.dtos.CreateReservationDTO;
import com.rapisolver.api.dtos.ReservationDTO;
import com.rapisolver.api.dtos.UpdateReservationDTO;
import com.rapisolver.api.exceptions.RapisolverException;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> findAll() throws RapisolverException;
    ReservationDTO findById(Long reservationId) throws RapisolverException;
    ReservationDTO createReservation(CreateReservationDTO createReservationDTO)  throws RapisolverException;
    ReservationDTO updateReservation(Long id, UpdateReservationDTO updateReservationDTO) throws RapisolverException;
}
