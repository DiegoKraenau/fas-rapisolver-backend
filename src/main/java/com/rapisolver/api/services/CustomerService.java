package com.rapisolver.api.services;

import com.rapisolver.api.exceptions.RapisolverException;

public interface CustomerService {
    String buySubscription(Long customerId, Long subscriptionId) throws RapisolverException;
}
