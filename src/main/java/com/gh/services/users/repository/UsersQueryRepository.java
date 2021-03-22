package com.gh.services.users.repository;

import com.gh.services.users.domain.Users;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class UsersQueryRepository extends QuerydslRepositorySupport {

    private JPAQueryFactory jpaQueryFactory;

    public UsersQueryRepository(JPAQueryFactory jpaQueryFactory) {
        super(Users.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


}
