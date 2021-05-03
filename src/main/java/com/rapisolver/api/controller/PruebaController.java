package com.rapisolver.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Tag(name = "Prueba", description = "Authentication API")
@RequestMapping("/api/prueba")
@RestController
public class PruebaController {
    @GetMapping
    //@PreAuthorize("isAuthenticated()")
    //@Operation(summary = "Get All Courses paginated by name", description = "Get All Courses paginated by name. Can filter by name (param optional)", tags = {"Course"}
    //        ,security = @SecurityRequirement(name = "bearerAuth"))
    public String resource(){
        return "Hola Mundo";
    }
}
