package com.rapisolver.api.services.impl;

import com.rapisolver.api.dtos.CreateReservationDTO;
import com.rapisolver.api.dtos.ReservationDTO;
import com.rapisolver.api.entities.*;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.*;
import com.rapisolver.api.services.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            reservation.setStatus(1);
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

}
