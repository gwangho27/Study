package com.gh.config.security;

import lombok.Data;

@Data
public class Role {
    private long id;
    private ERole name;

    public Role(){ };
}
