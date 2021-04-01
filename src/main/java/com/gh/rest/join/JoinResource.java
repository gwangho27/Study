package com.gh.rest.join;

import com.gh.config.security.ShaPasswordEncoder;
import com.gh.services.users.domain.Users;
import com.gh.services.users.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value="/api/v1/new")
public class JoinResource {

    private final UsersService usersService;
    private final ShaPasswordEncoder encoder;

    public JoinResource(UsersService usersService,
                        ShaPasswordEncoder encoder) {
        this.usersService = usersService;
        this.encoder = encoder;
    }

    // 먼가 더 고칠거야
    @PostMapping(value="")
    public ResponseEntity<?> join (Users user) {
        HashMap <String, Object> insertInfo = new HashMap<String, Object> ();
        user.setPassword(encoder.encode(user.getPassword()));
        user = usersService.insertUser(user);

        insertInfo.put("user", user);

        return ResponseEntity.ok(insertInfo);
    }
}
