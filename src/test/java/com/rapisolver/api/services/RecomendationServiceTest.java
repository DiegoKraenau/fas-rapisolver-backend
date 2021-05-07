package com.rapisolver.api.services;

import com.rapisolver.api.dtos.CreateRecommendationDTO;
import com.rapisolver.api.entities.*;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.RecommendationRepository;
import com.rapisolver.api.repositories.SupplierAttentionRepository;
import com.rapisolver.api.repositories.UserRepository;
import com.rapisolver.api.services.impl.RecommendationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

public class RecomendationServiceTest {

    private static final Long RECOMMENDATION_ID = 1L;
    private static final int MARK = 5;
    private static final String NOTE = "Excelente";

    private static final Long USER_ID = 1L;
    private static final String FIRSTNAME = "Usuario1";
    private static final String LASTNAME = "Unlastnammed";
    private static final String PHONE = "999999999";
    private static final String EMAIL = "usuario1@gmail.com";
    private static final String PASSWORD = "123456789";
    private static final Date BIRTHDATE = new Date(1990,Calendar.MAY,17);

    private static final Long SUPPLIER_ATTENTION_ID = 1L;
    private static final double PRICE = 50.0;

    private static final Long ROLE_ID = 1L;
    private static final String ROLE_NAME = "ROLE_CUSTOMER";
    private static final boolean CAN_PUBLISH = false;

    private static final Long ATTENTION_ID = 1L;
    private static final String ATTENTION_NAME = "Reparacion de luces";

    private static final Long CATEGORY_ID = 1L;
    private static final String CATEGORY_NAME = "Electricidad";

    private static final Long LOCATION_ID = 1L;
    private static final String COUNTRY = "Peru";
    private static final String STATE = "Lima";
    private static final String CITY = "Chorrillos";
    private static final String ADDRESS = "Los cedros de villa";

    private static final CreateRecommendationDTO CREATE_RECOMMENDATION_DTO = new CreateRecommendationDTO();
    private static final Recommendation RECOMMENDATION = new Recommendation();
    private static final User USER = new User();
    private static final SupplierAttention SUPPLIER_ATTENTION = new SupplierAttention();
    private static final Attention ATTENTION = new Attention();
    private static final Role ROLE = new Role();
    private static final Category CATEGORY = new Category();
    private static final Location LOCATION = new Location();

    private static final Optional<User> OPTIONAL_USER = Optional.of(USER);
    private static final Optional<SupplierAttention> OPTIONAL_SUPPLIER_ATTENTION = Optional.of(SUPPLIER_ATTENTION);
    //private static final Optional<Recommendation> OPTIONAL_RECOMMENDATION = Optional.of(RECOMMENDATION);

    @Mock
    private RecommendationRepository recommendationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SupplierAttentionRepository supplierAttentionRepository;

    @InjectMocks
    private RecommendationServiceImpl recommendationService;

    @Before
    public void init() throws RapisolverException {
        MockitoAnnotations.initMocks(this);

        ROLE.setId(ROLE_ID);
        ROLE.setName(ROLE_NAME);
        ROLE.setCanPublish(CAN_PUBLISH);

        LOCATION.setId(LOCATION_ID);
        LOCATION.setCountry(COUNTRY);
        LOCATION.setState(STATE);
        LOCATION.setCity(CITY);
        LOCATION.setAddress(ADDRESS);

        USER.setId(USER_ID);
        USER.setBirthdate(BIRTHDATE);
        USER.setEmail(EMAIL);
        USER.setPassword(PASSWORD);
        USER.setFirstName(FIRSTNAME);
        USER.setLastName(LASTNAME);
        USER.setPhone(PHONE);
        USER.setLocation(LOCATION);

        CATEGORY.setId(CATEGORY_ID);
        CATEGORY.setName(CATEGORY_NAME);

        ATTENTION.setId(ATTENTION_ID);
        ATTENTION.setName(ATTENTION_NAME);

        SUPPLIER_ATTENTION.setId(SUPPLIER_ATTENTION_ID);
        SUPPLIER_ATTENTION.setPrice(PRICE);
        SUPPLIER_ATTENTION.setAttention(ATTENTION);
        SUPPLIER_ATTENTION.setSupplier(USER);

        CREATE_RECOMMENDATION_DTO.setMark(MARK);
        CREATE_RECOMMENDATION_DTO.setNote(NOTE);
        CREATE_RECOMMENDATION_DTO.setUserId(USER_ID);
        CREATE_RECOMMENDATION_DTO.setSupplierAttentionId(SUPPLIER_ATTENTION_ID);

        RECOMMENDATION.setId(RECOMMENDATION_ID);
        RECOMMENDATION.setMark(MARK);
        RECOMMENDATION.setNote(NOTE);
        RECOMMENDATION.setUser(USER);
        RECOMMENDATION.setSupplierAttention(SUPPLIER_ATTENTION);
    }

    @Test
    public void createRecommendationTest() throws RapisolverException {
        Mockito.when(userRepository.findById(USER_ID)).thenReturn(OPTIONAL_USER);
        Mockito.when(supplierAttentionRepository.findById(SUPPLIER_ATTENTION_ID)).thenReturn(OPTIONAL_SUPPLIER_ATTENTION);
        Mockito.when(recommendationRepository.save(Mockito.any(Recommendation.class))).thenReturn(new Recommendation());

        recommendationService.create(CREATE_RECOMMENDATION_DTO);
    }

    @Test
    public void getAllRecommendationsTest() throws RapisolverException {
        Mockito.when(recommendationRepository.findAll()).thenReturn(Collections.singletonList(RECOMMENDATION));
        recommendationService.getAll();
    }

    @Test
    public void getBySupplierAttentionIdTest() throws RapisolverException {
        Mockito.when(recommendationRepository.findBySupplierAttentionId(SUPPLIER_ATTENTION_ID)).thenReturn(Collections.singletonList(RECOMMENDATION));
        recommendationService.getBySupplierAttentionId(SUPPLIER_ATTENTION_ID);
    }
}
