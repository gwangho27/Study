package com.gh.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QuerydslConfiguration {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory () {
        return new JPAQueryFactory(entityManager);
    }

    @Bean(name="transactionManager")
    public PlatformTransactionManager jpaTransactionManager(){
        return new JpaTransactionManager(entityManager.getEntityManagerFactory());
    }
}
