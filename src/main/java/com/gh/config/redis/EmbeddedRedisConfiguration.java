package com.gh.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Profile("local")
@Configuration
public class EmbeddedRedisConfiguration {

    private RedisServer redisServer;

    public EmbeddedRedisConfiguration(@Value("${spring.redis.port}") int port) {
        this.redisServer = new RedisServer(port);
    }

    @PostConstruct
    public void redisServer() {
//        redisServer.start();
    }

    @PreDestroy
    public void redisStop() {
        if(redisServer.isActive()){
            redisServer.stop();
        }
    }

}
