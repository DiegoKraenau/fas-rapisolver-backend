package com.rapisolver.api.service;

import com.rapisolver.api.controller.commons.RapiSolverResponse;
import com.rapisolver.api.dtos.LoginRest;
import com.rapisolver.api.dtos.SignUpRest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<RapiSolverResponse> registerUser(SignUpRest signUpRequest);
    ResponseEntity<RapiSolverResponse> authenticateUser(LoginRest loginRequest);
}
