package com.gh.rest.authentication;

import com.gh.config.security.CustomAuthenticationProvider;
import com.gh.config.security.ShaPasswordEncoder;
import com.gh.config.security.jwt.JwtResponse;
import com.gh.config.security.jwt.JwtUtil;
import com.gh.services.common.domain.User;
import com.gh.services.users.domain.Users;
import com.gh.services.users.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginResource {

    private final CustomAuthenticationProvider authenticationProvider ;
    private final JwtUtil jwtUtil;

    public LoginResource(CustomAuthenticationProvider authenticationProvider,
                         JwtUtil jwtUtil,
                         UsersService usersService,
                         ShaPasswordEncoder passwordEncoder) {
        this.authenticationProvider = authenticationProvider;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value="login")
    public ResponseEntity<?> login (User loginUser) {

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

        return ResponseEntity.ok(new JwtResponse(jwt,
                                                user.getId(),
                                                user.getUsername(),
                                                200,
                                                role));
    }
}
