package com.gh.services.users.repository;

import com.gh.services.users.domain.QUsers;
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

    public Users findByUsername (String username) {
        return jpaQueryFactory
                .select(QUsers.users)
                .from(QUsers.users)
                .where(QUsers.users.username.eq(username)).fetchOne();
    }

}
