package com.rapisolver.api.security.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";

    private Long id;
    private String email;
    private String role;
    private String firstName;

    //List<String>
    public JwtResponse(String accessToken, Long id, String email, String roles, String firstName){
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.role = roles;
        this.firstName = firstName;
    }
}
