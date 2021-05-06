package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CreateReservationDTO;
import com.rapisolver.api.entities.Location;
import com.rapisolver.api.entities.Reservation;
import com.rapisolver.api.entities.SupplierAttentions;
import com.rapisolver.api.entities.User;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.LocationRepository;
import com.rapisolver.api.repositories.ReservationRepository;
import com.rapisolver.api.repositories.SupplierAttentionRepository;
import com.rapisolver.api.repositories.UserRepository;
import com.rapisolver.api.services.impl.ReservationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

public class ReservationServiceTest {

    private static final Date DATE_REQUESTED = new Date();

    private static final String COUNTRY = " Perú";

    private static final String STATE = " Lima";

    private static final String CITY = " Lima";

    private static final String ADDRESS = " Av. Volcano 360";

    private static final Long USER_ID = 1L;

    private static final Long SUPPLIER_ATTENTION_ID = 1L;

    private static final String COMMENT = " Lo necesito lo más rapido urgente";

    private static final User USER = new User();

    private static final String EMAIL = " diegokraenau@gmail.com";

    private static final String PASSWORD = " diego2009";

    private static final Optional<User> OPTIONAL_USER = Optional.of(USER);

    private static final SupplierAttentions SUPPLIER_ATTENTIONS = new SupplierAttentions();

    private static final  Double PRICE = 300.00;

    private static final Optional<SupplierAttentions> OPTIONAL_SUPPLIER_ATTENTIONS = Optional.of(SUPPLIER_ATTENTIONS);

    CreateReservationDTO  CREATE_RESERVATION_DTO = new CreateReservationDTO();

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private SupplierAttentionRepository supplierAttentionRepository;

    @InjectMocks
    private ReservationServiceImpl reservationServiceImpl;

   @Before
    public void init() throws RapisolverException{
       MockitoAnnotations.initMocks(this);
       USER.setId(USER_ID);
       USER.setEmail(EMAIL);
       USER.setPassword(PASSWORD);

       SUPPLIER_ATTENTIONS.setId(SUPPLIER_ATTENTION_ID);
       SUPPLIER_ATTENTIONS.setPrice(PRICE);

       CREATE_RESERVATION_DTO.setDateRequested(DATE_REQUESTED);
       CREATE_RESERVATION_DTO.setCountry(COUNTRY);
       CREATE_RESERVATION_DTO.setState(STATE);
       CREATE_RESERVATION_DTO.setCity(CITY);
       CREATE_RESERVATION_DTO.setAddress(ADDRESS);
       CREATE_RESERVATION_DTO.setUserId(USER_ID);
       CREATE_RESERVATION_DTO.setSupplierAttentionId(SUPPLIER_ATTENTION_ID);
       CREATE_RESERVATION_DTO.setComment(COMMENT);
   }

   @Test
   public void  createReservationTest() throws RapisolverException{
       Mockito.when(locationRepository.save(Mockito.any(Location.class))).thenReturn(new Location());
       Mockito.when(userRepository.findById(USER_ID)).thenReturn(OPTIONAL_USER);
       Mockito.when(supplierAttentionRepository.findById(SUPPLIER_ATTENTION_ID)).thenReturn(OPTIONAL_SUPPLIER_ATTENTIONS);
       Mockito.when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(new Reservation());

       reservationServiceImpl.createReservation(CREATE_RESERVATION_DTO);


   }
}
