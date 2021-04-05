package com.gh.config.security;

import com.gh.services.common.domain.User;
import com.gh.services.users.domain.Users;
import com.gh.services.users.service.UsersService;
import com.gh.utils.auth.AuthoritiesUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailService userDetailsService;
    private final ShaPasswordEncoder encoder;
    private final UsersService usersService;

    public CustomAuthenticationProvider (CustomUserDetailService userDetailsService,
                                         ShaPasswordEncoder encoder,
                                         UsersService usersService)  {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
        this.usersService = usersService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String password = null;
        String username = token.getName();

        User user = new User();
        User findUser = null;

        if (StringUtils.hasText(username)) {
            findUser = (User) userDetailsService.loadUserByUsername(username);
        }

        if (ObjectUtils.isEmpty(findUser)) {
            throw new UsernameNotFoundException("Invalid username");
        }
        password = token.getCredentials().toString();
        String StorePassword = usersService.getStorePassword(findUser);

        if(!encoder.matches(password, StorePassword)) {
            throw new BadCredentialsException("Invalid password");
        }
        Collection<? extends GrantedAuthority> authorities = AuthoritiesUtils.createAuthorities(findUser);
        findUser.setAuthorities(authorities);

        findUser.setPassword("");
        return new UsernamePasswordAuthenticationToken(findUser, null,authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
