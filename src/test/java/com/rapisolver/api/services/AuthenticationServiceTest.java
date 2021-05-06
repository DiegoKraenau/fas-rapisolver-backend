package com.rapisolver.api.services;

import com.rapisolver.api.controllers.commons.RapiSolverResponse;
import com.rapisolver.api.controllers.commons.ResponseConstants;
import com.rapisolver.api.dtos.SignUpRest;
import com.rapisolver.api.dtos.UserDTO;
import com.rapisolver.api.entities.*;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.RoleRepository;
import com.rapisolver.api.repositories.UserRepository;
import com.rapisolver.api.services.impl.AuthenticationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class AuthenticationServiceTest {


    private static final String FIRST_NAME = "Jesus";
    private static final String LAST_NAME = "Duran";
    private static final String EMAIL = "jesusgonzaloduran@gmail.com";
    private static final String PASSWORD = "jeshu666";
    private static final String PHONE = "994667123";
    private static final Date BIRTHDATE = new Date(1999, Calendar.AUGUST,12);
    private static final String COUNTRY="PERU";
    private static final String STATE="LIMA";
    private static final String CITY="SURCO";
    private static final String ADDRESS="Av. Tomas Marsano 115";

    private static final SignUpRest NEW_USER = new SignUpRest();


    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    PasswordEncoder encoder;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    private AuthenticationServiceImpl underTest;


    @Before
    public void  setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void  registerUserSuccesfully() throws RapisolverException {

        //Given user completes correctly the register form
        NEW_USER.setFirstname(FIRST_NAME);
        NEW_USER.setLastname(LAST_NAME);
        NEW_USER.setEmail(EMAIL);
        NEW_USER.setPassword(PASSWORD);
        NEW_USER.setPhone(PHONE);
        NEW_USER.setBirthdate(BIRTHDATE);
        NEW_USER.setCountry(COUNTRY);
        NEW_USER.setState(STATE);
        NEW_USER.setCity(CITY);
        NEW_USER.setAddress(ADDRESS);

        //And the emails is not already taken
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        // And Role customer exists

        Mockito.when(roleRepository.findByName("ROLE_CUSTOMER")).thenReturn(Optional.of(new Role()));

        Mockito.when(encoder.encode(Mockito.anyString())).thenReturn("$$dffsdfae548");

        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(new UserDTO());
        // And user is saved by repository
        Mockito.when(userRepository.save(Mockito.any(Customer.class))).thenReturn(new Customer());

        ResponseEntity<RapiSolverResponse> responseEntity = underTest.registerUser(NEW_USER);

        //Then user is correctly registered

        Assert.assertEquals(responseEntity.getBody().getMessage(), "USER SUCCESSFULLY REGISTERED");
        Assert.assertEquals(responseEntity.getBody().getCode(), ResponseConstants.SUCCESS_CODE);

    }

    @Test
    public void  userSuccessfullyLogin() throws RapisolverException {

        //Given user completes correctly the register form
        NEW_USER.setFirstname(FIRST_NAME);
        NEW_USER.setLastname(LAST_NAME);
        NEW_USER.setEmail(EMAIL);
        NEW_USER.setPassword(PASSWORD);
        NEW_USER.setPhone(PHONE);
        NEW_USER.setBirthdate(BIRTHDATE);
        NEW_USER.setCountry(COUNTRY);
        NEW_USER.setState(STATE);
        NEW_USER.setCity(CITY);
        NEW_USER.setAddress(ADDRESS);

        //And the emails is not already taken
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        // And Role customer exists

        Mockito.when(roleRepository.findByName("ROLE_CUSTOMER")).thenReturn(Optional.of(new Role()));

        Mockito.when(encoder.encode(Mockito.anyString())).thenReturn("$$dffsdfae548");

        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(new UserDTO());
        // And user is saved by repository
        Mockito.when(userRepository.save(Mockito.any(Customer.class))).thenReturn(new Customer());

        ResponseEntity<RapiSolverResponse> responseEntity = underTest.registerUser(NEW_USER);

        //Then user is correctly registered

        Assert.assertEquals(responseEntity.getBody().getMessage(), "USER SUCCESSFULLY REGISTERED");
        Assert.assertEquals(responseEntity.getBody().getCode(), ResponseConstants.SUCCESS_CODE);

    }



}
