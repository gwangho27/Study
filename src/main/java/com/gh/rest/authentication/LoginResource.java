package com.gh.rest.authentication;

import com.gh.config.security.CustomAuthenticationProvider;
import com.gh.config.security.ShaPasswordEncoder;
import com.gh.config.security.jwt.JwtResponse;
import com.gh.config.security.jwt.JwtUtil;
import com.gh.services.common.domain.User;
import com.gh.services.users.domain.AccessHistory;
import com.gh.services.users.domain.Users;
import com.gh.services.users.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginResource {

    private final CustomAuthenticationProvider authenticationProvider ;
    private final JwtUtil jwtUtil;
    private final UsersService usersService;
    public LoginResource(CustomAuthenticationProvider authenticationProvider,
                         JwtUtil jwtUtil,
                         UsersService usersService,
                         ShaPasswordEncoder passwordEncoder) {
        this.authenticationProvider = authenticationProvider;
        this.usersService = usersService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value="login")
    public ResponseEntity<?> login (@RequestBody User loginUser) {

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
        Authentication authenticate = authenticationProvider.authenticate(authRequest);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authenticate);
        String jwt = jwtUtil.generateJwtToken(authenticate);
        User user = (User) authenticate.getPrincipal();

        List<String> role = user.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        AccessHistory saveHistory = usersService.insertAccessHistory(
                AccessHistory.builder()
                .users(new Users(user.getId()))
                .build());

        return ResponseEntity.ok(
                JwtResponse.
                        builder()
                        .token(jwt)
                        .id(user.getId())
                        .username(user.getUsername())
                        .roles(role)
                        .state(200).build());
    }
}
