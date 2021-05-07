package com.rapisolver.api.controllers;

import com.rapisolver.api.controllers.commons.RapiSolverResponse;
import com.rapisolver.api.dtos.LoginRest;
import com.rapisolver.api.dtos.SignUpRest;
import com.rapisolver.api.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Authentication", description = "API is Ready")
@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signup")
    @Operation(summary = "User Registration", description = "Registration for both customer and supplier user", tags = {"Authentication"})
    public ResponseEntity<RapiSolverResponse> registerUser(@Valid @RequestBody SignUpRest signUpRequest) {
        return this.authenticationService.registerUser(signUpRequest);
    }

    @PostMapping("/signin")
    @Operation(summary = "User Log in", description = "Log in for customer and supplier user. Returns JWT and user info", tags = {"Authentication"})
    public ResponseEntity<RapiSolverResponse> authenticateUser(@Valid @RequestBody LoginRest loginRequest) {
        return this.authenticationService.authenticateUser(loginRequest);
    }
}
