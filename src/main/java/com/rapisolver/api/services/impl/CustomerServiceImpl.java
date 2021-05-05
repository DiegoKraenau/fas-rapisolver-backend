package com.rapisolver.api.services.impl;

import com.rapisolver.api.entities.Customer;
import com.rapisolver.api.entities.Location;
import com.rapisolver.api.entities.Role;
import com.rapisolver.api.entities.Supplier;
import com.rapisolver.api.exceptions.InternalServerErrorException;
import com.rapisolver.api.exceptions.NotFoundException;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.repositories.CustomerRepository;
import com.rapisolver.api.repositories.LocationRepository;
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

    @Autowired
    LocationRepository locationRepository;


    public static final ModelMapper modelMapper=new ModelMapper();

    @Override
    public String buySubscription(Long customerId, Long subscriptionId) throws RapisolverException {

        try {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(()->new NotFoundException("CUSTOMER_NOT_FOUND"));

            Role roleSup=roleRepository.findByName("ROLE_SUPPLIER")
                    .orElseThrow(()->new NotFoundException("ROLE_NOT_FOUND"));

            Supplier supplier = new Supplier();


            Location location = new Location(customer.getLocation().getCountry(), customer.getLocation().getState(),customer.getLocation().getCity(), customer.getLocation().getAddress());
            Location savedLocation = locationRepository.save(location);

            //TODO Cambiar  el rol

            //supplier = modelMapper.map(customer,Supplier.class);
            supplier.setFirstName(customer.getFirstName());
            supplier.setLastName(customer.getLastName());
            supplier.setEmail(customer.getEmail());
            supplier.setPassword(customer.getPassword());
            supplier.setPhone(customer.getPhone());
            supplier.setBirthdate(customer.getBirthdate());
            supplier.setRole(roleSup);
            supplier.setLocation(savedLocation);
            //TODO: COMERCIAL NAME

            Supplier savedsup = supplierRepository.save(supplier);

            customerRepository.deleteById(customerId);

            return "Felicidades te registrado a un plan, ahora puedes publicar tus servicios";

        }catch (Exception e){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR PAAA");
        }
    }
}
