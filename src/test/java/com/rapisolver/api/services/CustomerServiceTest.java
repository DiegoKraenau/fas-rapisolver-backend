package com.rapisolver.api.services;

import com.rapisolver.api.entities.*;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.CustomerRepository;
import com.rapisolver.api.repositories.SupplierRepository;
import com.rapisolver.api.services.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CustomerServiceTest {

    private static final Long CUSTOMER_ID=1L;

    private static final Long SUBSCRIPTION_ID=1L;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    private static final Customer CUSTOMER = new Customer();

    private static final Optional<Customer> OPTIONAL_CUSTOMER = Optional.of(CUSTOMER);

    @Before
    public void init() throws RapisolverException {
        MockitoAnnotations.initMocks(this);
        CUSTOMER.setId(CUSTOMER_ID);
    }

    @Test
    public void  buySubscriptionTest() throws RapisolverException{
        Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(OPTIONAL_CUSTOMER);
      //  Mockito.when(customerRepository.deleteById(CUSTOMER_ID));
        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(new Supplier());
        customerServiceImpl.buySubscription(CUSTOMER_ID,SUBSCRIPTION_ID);
    }

}
