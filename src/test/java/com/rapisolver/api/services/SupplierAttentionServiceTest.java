package com.rapisolver.api.services;

import com.rapisolver.api.dtos.SignUpRest;
import com.rapisolver.api.dtos.SupplierAtenttionAttDTO;
import com.rapisolver.api.dtos.SupplierAtenttionSupDTO;
import com.rapisolver.api.entities.*;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.*;
import com.rapisolver.api.services.impl.SupplierAttentionServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SupplierAttentionServiceTest {

    private static final Long LOCATOR_ID = 1L;
    private static final Location LOCATION = new Location();



    private static final List<User> USER_LIST = new ArrayList<>();
    private static final Long USER_ID = 1L;
    private static final User USER = new User();
    private static final String FIRST_NAME = "DANIEL";
    private static final String LAST_NAME = "JAUREGUI";
    private static final String EMAIL = "danklp132@gmail.com";
    private static final String PASSWORD = "dan123";
    private static final String PHONE = "123456789";
    private static final Date BIRTHDATE = new Date(2000, Calendar.NOVEMBER,8);
    private static final String COUNTRY="PERU";
    private static final String STATE="LIMA";
    private static final String CITY="INDEPENDENCIA";
    private static final String ADDRESS="Jr. Pacaritambo 141";


    private static final Long CATEGORY_ID = 1L;
    private static final Category CATEGORY = new Category();
    private static final String NAME_CATEGORY = "EDUCACION";
    private static final String DETAIL_CATEGORY = "EDUCACION";
    private static final List<Attention> ATTENTIONS_LIST = new ArrayList<>();


    private static final Long ATTENTION_ID = 1L;
    private static final Attention ATTENTION = new Attention();
    private static final List<SupplierAttention> SUPPLIER_ATTENTIONS_LIST = new ArrayList<>();
    private static final String NAME_ATTENTION = "MAESTRO";
    private static final String DETAIL_ATTENTION = "ENSEÑANZA";


    private static final SupplierAttention SUPPLIER_ATTENTION = new SupplierAttention();
    private static final Long SA_ID = 1L;
    private static final double PRICE = 300.00;
    private static final List<Reservation> RESERVATION_LIST = new ArrayList<>();


    private static final Role ROLE = new Role();
    private static final Long ROLE_ID = 2L;
    private static final String NAME_ROLE = "ROLE_SUPPLIER";
    private static final boolean CAN_PUBLISH = true;


    @Mock
    AttentionRepository attentionRepository;

    @Mock
    SupplierAttentionRepository supplierAttentionRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    SupplierAttentionServiceImpl supplierAttentionServiceImpl;

    @Before
    public void init() throws RapisolverException {
        MockitoAnnotations.initMocks(this);

        USER.setFirstName(FIRST_NAME);
        USER.setEmail(EMAIL);
        USER.setPassword(PASSWORD);
        USER.setId(USER_ID);
        USER.setLastName(LAST_NAME);
        USER.setBirthdate(BIRTHDATE);
        USER.setLocation(LOCATION);
        USER.setSupplierAttentions(SUPPLIER_ATTENTIONS_LIST);
        USER.setPhone(PHONE);
        USER.setReservations(RESERVATION_LIST);
        USER.setRole(ROLE);

        ATTENTION.setId(ATTENTION_ID);
        ATTENTION.setSupplierAttentions(SUPPLIER_ATTENTIONS_LIST);
        ATTENTION.setCategory(CATEGORY);
        ATTENTION.setDetail(DETAIL_ATTENTION);
        ATTENTION.setName(NAME_ATTENTION);

        ROLE.setName(NAME_ROLE);
        ROLE.setId(ROLE_ID);
        ROLE.setCanPublish(CAN_PUBLISH);

        CATEGORY.setId(CATEGORY_ID);
        CATEGORY.setName(NAME_CATEGORY);
        CATEGORY.setAttentions(ATTENTIONS_LIST);
        CATEGORY.setDescription(DETAIL_CATEGORY);

        LOCATION.setAddress(ADDRESS);
        LOCATION.setId(LOCATOR_ID);
        LOCATION.setReservations(RESERVATION_LIST);
        LOCATION.setCity(CITY);
        LOCATION.setState(STATE);
        LOCATION.setCountry(COUNTRY);
        LOCATION.setUser(USER);


        SUPPLIER_ATTENTION.setAttention(ATTENTION);
        SUPPLIER_ATTENTION.setSupplier(USER);
        SUPPLIER_ATTENTION.setReservations(RESERVATION_LIST);
        SUPPLIER_ATTENTION.setId(SA_ID);
        SUPPLIER_ATTENTION.setPrice(PRICE);


    }

    @Test
    public void findSuppliersByAttentionTest() throws RapisolverException{
        final Attention attention = new Attention();
        Mockito.when(attentionRepository.findByNameContaining(NAME_ATTENTION)).thenReturn(Arrays.asList(attention));
        final SupplierAttention supplierAttention = new SupplierAttention();
        Mockito.when(supplierAttentionRepository.findByAttentionIn(ATTENTIONS_LIST)).thenReturn(Arrays.asList(supplierAttention));
        supplierAttentionServiceImpl.findSuppliersByAttention(NAME_ATTENTION);

    }

    @Test
    public void findAttentionsBySuppliersTest() throws RapisolverException{
        final User supplier = new User();
        Mockito.when(userRepository.findByFirstNameContaining(FIRST_NAME)).thenReturn(Arrays.asList(supplier));
        final SupplierAttention supplierAttention = new SupplierAttention();
        Mockito.when(supplierAttentionRepository.findBySupplierIn(USER_LIST)).thenReturn(Arrays.asList(supplierAttention));
        supplierAttentionServiceImpl.findAttentionsBySuppliers(FIRST_NAME);

    }

    @Test
    public void findAttentionsReservedTest() throws RapisolverException {
        Mockito.when(supplierAttentionRepository.findRecord(USER_ID)).thenReturn(SUPPLIER_ATTENTIONS_LIST);
        supplierAttentionServiceImpl.findAttentionsReserved(USER_ID);
    }
}