package com.rapisolver.api.services.impl;

import com.rapisolver.api.controllers.commons.RapiSolverResponse;
import com.rapisolver.api.controllers.commons.ResponseConstants;
import com.rapisolver.api.dtos.LoginRest;
import com.rapisolver.api.dtos.SignUpRest;
import com.rapisolver.api.dtos.UserDTO;
import com.rapisolver.api.entities.*;
import com.rapisolver.api.repositories.RoleRepository;

import com.rapisolver.api.repositories.UserRepository;
import com.rapisolver.api.security.jwt.JwtUtils;
import com.rapisolver.api.security.response.JwtResponse;
import com.rapisolver.api.security.service.UserDetailsImpl;
import com.rapisolver.api.services.AuthenticationService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;
/*
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SupplierRepository supplierRepository;

*/

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    JwtUtils jwtUtils;


    @Override
    public ResponseEntity<RapiSolverResponse> registerUser(SignUpRest signUpRequest) {
        try {
            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(RapiSolverResponse.builder()
                                .code(ResponseConstants.ERROR_CODE)
                                .message("EMAIL IS ALREADY TAKEN")
                                .build());
            }


            Role userRole = roleRepository.findByName("ROLE_CUSTOMER")
                    .orElseThrow(() -> new RuntimeException("ROLE NOT FOUND"));

            // Create new user's account

            User savedUser;

            Customer customer = new Customer(signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhone(), signUpRequest.getBirthdate(), userRole,null);

            Location location = new Location(signUpRequest.getCountry(), signUpRequest.getState(), signUpRequest.getCity(), signUpRequest.getAddress());

            customer.setLocation(location);
            savedUser = userRepository.save(customer);

            UserDTO userDTO = modelMapper.map(savedUser,UserDTO.class);

            return ResponseEntity.ok(
                    RapiSolverResponse.builder()
                            .code(ResponseConstants.SUCCESS_CODE)
                            .message("USER SUCCESSFULLY REGISTERED")
                            .data(userDTO)
                            .build()
            );
        } catch (Exception e) {
            String stacktrace = ExceptionUtils.getStackTrace(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(RapiSolverResponse.builder()
                            .code(ResponseConstants.ERROR_CODE)
                            .message("INTERNAL_ERROR"+ stacktrace)
                            .build());
        }



    }

    @Override
    public ResponseEntity<RapiSolverResponse> authenticateUser(LoginRest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            String roleOne="";
            for(String r: roles){
                roleOne=roleOne.concat(r);
            }

            JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(), roleOne, userDetails.getFirstName());

            return ResponseEntity.ok(RapiSolverResponse.builder()
                    .code(ResponseConstants.SUCCESS_CODE)
                    .message("SUCCESSFULL LOGIN")
                    .data(jwtResponse)
                    .build());


        } catch(BadCredentialsException e){

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(
                            RapiSolverResponse.builder()
                                    .code(ResponseConstants.ERROR_CODE)
                                    .message("INCORRECT CREDENTIALS")
                                    .build());

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            RapiSolverResponse.builder()
                                    .code(ResponseConstants.ERROR_CODE)
                                    .message(e.getMessage())
                                    .build());
        }

    }
}
