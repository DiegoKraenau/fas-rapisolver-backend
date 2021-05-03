package com.rapisolver.api.services.impl;

import com.rapisolver.api.controllers.commons.RapiSolverResponse;
import com.rapisolver.api.controllers.commons.ResponseConstants;
import com.rapisolver.api.dtos.LoginRest;
import com.rapisolver.api.dtos.SignUpRest;
import com.rapisolver.api.dtos.UserDTO;
import com.rapisolver.api.entities.Customer;
import com.rapisolver.api.entities.Role;
import com.rapisolver.api.entities.Supplier;
import com.rapisolver.api.entities.User;
import com.rapisolver.api.repositories.RoleRepository;

import com.rapisolver.api.repositories.UserRepository;
import com.rapisolver.api.security.jwt.JwtUtils;
import com.rapisolver.api.security.response.JwtResponse;
import com.rapisolver.api.security.service.UserDetailsImpl;
import com.rapisolver.api.services.AuthenticationService;
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

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(RapiSolverResponse.builder()
                            .code(ResponseConstants.ERROR_CODE)
                            .message("EMAIL IS ALREADY TAKEN")
                            .build());
        }

        String strRole = signUpRequest.getRole();
        Role userRole;
        if (strRole == null) {
            return ResponseEntity.badRequest().body(RapiSolverResponse.builder()
                    .code(ResponseConstants.ERROR_CODE)
                    .message("ROLE CANNOT BE NULL")
                    .build());
        } else {
            switch (strRole) {
                case "ROLE_CUSTOMER":
                    userRole  = roleRepository.findByName("ROLE_CUSTOMER")
                            .orElseThrow(() -> new RuntimeException("ROLE NOT FOUND"));
                    break;
                case "ROLE_SUPPLIER":
                    userRole = roleRepository.findByName("ROLE_SUPPLIER")
                            .orElseThrow(() -> new RuntimeException("ROLE NOT FOUND"));
                    break;
                default:
                    throw new RuntimeException("ROLE NOT FOUND");

            }

        }

        // Create new user's account

        User savedUser;

        if(userRole.getName().equals("ROLE_CUSTOMER")){
            Customer customer = new Customer(signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhone(), signUpRequest.getBirthdate(), userRole,null);

            savedUser = userRepository.save(customer);
        } else {
            Supplier supplier = new Supplier(signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhone(), signUpRequest.getBirthdate(), userRole,"");

            savedUser = userRepository.save(supplier);
       }

        UserDTO userDTO = modelMapper.map(savedUser,UserDTO.class);

        return ResponseEntity.ok(
                RapiSolverResponse.builder()
                        .code(ResponseConstants.SUCCESS_CODE)
                        .message("USER SUCCESSFULLY REGISTERED")
                        .data(userDTO)
                        .build()
        );
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
