package com.gh.services.users.service;

import com.gh.services.users.domain.AccessHistory;
import com.gh.services.users.repository.AccessHistoryRepository;
import com.gh.services.users.repository.UsersQueryRepository;
import com.gh.services.users.repository.UsersRepository;
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

}
