package com.rapisolver.api.services.impl;

import com.rapisolver.api.entities.Customer;
import com.rapisolver.api.entities.Role;
import com.rapisolver.api.entities.Supplier;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.CustomerRepository;
import com.rapisolver.api.repositories.RoleRepository;
import com.rapisolver.api.repositories.SupplierRepository;
import com.rapisolver.api.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerServiceImpl  implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    RoleRepository roleRepository;


    public static final ModelMapper modelMapper=new ModelMapper();

    @Override
    public String buySubscription(Long customerId, Long subscriptionId) throws RapisolverException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new NotFoundException("CUSTOMER_NOT_FOUND"));
        Supplier supplier = new Supplier();
        Role roleSup=roleRepository.findByName("ROLE_SUPPLIER")
                .orElseThrow(()->new NotFoundException("ROLE_NOT_FOUND"));
        try {
            //TODO Cambiar  el rol

            customerRepository.deleteById(customerId);

            //supplier = modelMapper.map(customer,Supplier.class);
            supplier.setFirstName(customer.getFirstName());
            supplier.setLastName(customer.getLastName());
            supplier.setEmail(customer.getEmail());
            supplier.setPassword(customer.getPassword());
            supplier.setPhone(customer.getPhone());
            supplier.setBirthdate(customer.getBirthdate());
            supplier.setRole(roleSup);
            //TODO: COMERCIAL NAME

            Supplier savedsup = supplierRepository.save(supplier);

            return "Felicidades te registrado a un plan, ahora puedes publicar tus servicios";

        }catch (Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR PAAA");
        }
    }
}
