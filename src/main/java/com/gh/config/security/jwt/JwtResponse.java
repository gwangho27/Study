package com.gh.config.security.jwt;


import com.gh.config.security.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private String token ;
    private Long id;
    private String username;
    private int state;
    private List<String> roles;

    @Builder
    public JwtResponse( String token ,
                       Long id,
                       String username,
                       int state,
                        List<String> roles ){
        this.token = token;
        this.id = id;
        this.username = username;
        this.state = state;
        this.roles = roles;
    }

}
