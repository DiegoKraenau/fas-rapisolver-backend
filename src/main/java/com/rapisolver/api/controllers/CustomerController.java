package com.rapisolver.api.controllers;

import com.rapisolver.api.dtos.CreateReservationDTO;
import com.rapisolver.api.dtos.ReservationDTO;
import com.rapisolver.api.exceptions.RapisolverException;
import com.rapisolver.api.response.RapisolverResponse;
import com.rapisolver.api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/rapiSolver/"+"/v1")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    //@ResponseStatus(HttpStatus.OK)
    @PostMapping("/customer/{customerId}/buySubscription/{subscriptionId}")
    public RapisolverResponse<String> buySubscription(@PathVariable Long customerId,@PathVariable Long subscriptionId) throws RapisolverException {
        return new RapisolverResponse<>(200,String.valueOf(HttpStatus.OK),"OK",customerService.buySubscription(customerId,subscriptionId));
    }

}
