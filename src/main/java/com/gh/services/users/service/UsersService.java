package com.gh.services.users.service;

import com.gh.services.common.domain.User;
import com.gh.services.users.domain.AccessHistory;
import com.gh.services.users.domain.Users;
import com.gh.services.users.repository.AccessHistoryRepository;
import com.gh.services.users.repository.UsersQueryRepository;
import com.gh.services.users.repository.UsersRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final AccessHistoryRepository accessHistoryRepository;
    private final UsersQueryRepository usersQueryRepository;
    private final UsersRepository usersRepository;

    public UsersService (AccessHistoryRepository accessHistoryRepository,
                         UsersQueryRepository usersQueryRepository,
                         UsersRepository usersRepository){
        this.accessHistoryRepository = accessHistoryRepository;
        this.usersQueryRepository = usersQueryRepository;
        this.usersRepository = usersRepository;
    }

    public User findByUsername (String username) {

        Users user = usersQueryRepository.findByUsername(username);
        return new User(user.getId(),user.getUsername(),user.getPassword());
    }

    public String getStorePassword (User user) {
        return usersRepository.findById(user.getId()).get().getPassword();
    }

    public Users insertUser (Users user) {
        return usersRepository.save(user);
    }
}
