package com.gh.config.security;

import com.gh.services.common.domain.User;
import com.gh.services.users.service.UsersService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private final UsersService usersService;

    public CustomUserDetailService ( UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser =  usersService.findByUsername(username);

        if (ObjectUtils.isEmpty(findUser)){
            throw new UsernameNotFoundException("Invalid username");
        }

        return User.build(findUser);
    }

}
