package com.rapisolver.api.services.impl;

import com.rapisolver.api.dtos.CreateReservationDTO;
import com.rapisolver.api.dtos.ReservationDTO;
import com.rapisolver.api.dtos.UpdateReservationDTO;
import com.rapisolver.api.entities.*;
import com.rapisolver.api.enums.StatusOrder;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.*;
import com.rapisolver.api.services.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl  implements ReservationService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SupplierAttentionRepository supplierAttentionRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public static final ModelMapper modelMapper=new ModelMapper();

    @Override
    public List<ReservationDTO> findAll() throws RapisolverException {
        List<Reservation> reservations;
        try {
            reservations = reservationRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException("Error al obtener todas las reservas");
        }

        return reservations.stream().map(reservation -> modelMapper.map(reservation, ReservationDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ReservationDTO findById(Long reservationId) throws RapisolverException {
        Reservation reservationBD = reservationRepository.findById(reservationId).orElseThrow(() -> new NotFoundException("No se encontro la reservacion con Id:" + reservationId));
        return modelMapper.map(reservationBD, ReservationDTO.class);
    }

    @Override
    public ReservationDTO createReservation(CreateReservationDTO createReservationDTO) throws RapisolverException {
            Reservation reservation = new Reservation();
            Location location = modelMapper.map(createReservationDTO,Location.class);
            User usuario= new User();
            SupplierAttentions supplierAttention = new SupplierAttentions();

            usuario=userRepository.findById(createReservationDTO.getUserId())
                    .orElseThrow(()->new NotFoundException("USER_NOT_FOUND"));

            supplierAttention=supplierAttentionRepository.findById(createReservationDTO.getSupplierAttentionId())
                    .orElseThrow(()->new NotFoundException("ATTENTION_NOT_FOUND"));

        try {
            ReservationDTO reservationDTO = new ReservationDTO();
            location = locationRepository.save(location);
            reservation.setLocation(location);
            reservation.setUser(usuario);
            reservation.setSupplierAttention(supplierAttention);
            reservation.setDateRequested(createReservationDTO.getDateRequested());
            reservation.setComment(createReservationDTO.getComment());
            /*
            1:Pendiente
            2:Finalizado
             */
            reservation.setStatus(StatusOrder.ORDERED);
            reservation = reservationRepository.save(reservation);

            //Mapping
            reservationDTO = modelMapper.map(reservation,ReservationDTO.class);
            reservationDTO.setCountry(location.getCountry());
            reservationDTO.setAddress(location.getAddress());
            reservationDTO.setCity(location.getCity());
            reservationDTO.setState(location.getState());

            return reservationDTO;
        }catch (Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR");
        }

    }

    @Override
    public ReservationDTO updateReservation(Long id, UpdateReservationDTO updateReservationDTO) throws RapisolverException {
        Reservation reservationDB = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservacion a actualizar no encontrado"));

        Location location = modelMapper.map(updateReservationDTO,Location.class);
        User usuario = userRepository.findById(updateReservationDTO.getUserId())
                                        .orElseThrow(()->new NotFoundException("USER_NOT_FOUND"));
        SupplierAttentions supplierAttention = supplierAttentionRepository.findById(updateReservationDTO.getSupplierAttentionId())
                .orElseThrow(()->new NotFoundException("ATTENTION_NOT_FOUND"));

        try {
            location = locationRepository.save(location);

            reservationDB.setComment(updateReservationDTO.getComment());
            reservationDB.setDateRequested(updateReservationDTO.getDateRequested());
            reservationDB.setStatus(StatusOrder.ORDERED);
            reservationDB.setUser(usuario);
            reservationDB.setLocation(location);
            reservationDB.setSupplierAttention(supplierAttention);

            reservationDB = reservationRepository.save(reservationDB);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error de base de datos al actualizar el rol");
        }

        return modelMapper.map(reservationDB,ReservationDTO.class);
    }

}
