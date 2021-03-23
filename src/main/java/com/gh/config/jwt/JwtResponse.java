package com.gh.config.jwt;


import lombok.Builder;

import java.util.List;

public class JwtResponse {

    private String token ;
    private Long id;
    private String username;
    private int state;
    /**
     * user role 추가하기
     */
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
