package com.gh.config.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Configuration
@EnableRedisHttpSession
public class RedisConfiguration {

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.password}")
    private String redisPassword;
    /**
     *  Redis
     **/
    @Bean
    public RedisConnectionFactory redisConnectionFactory () {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(redisPort);
        redisStandaloneConfiguration.setPassword(redisPassword);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String , Object> redisTemplate () {
        RedisTemplate<String , Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }

    /**
     *  Embedded Redis
     */
    /*
    @Bean
    public RedisConnectionFactory redisConnectionFactory () {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost,redisPort));
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate () {
        RedisTemplate <?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
*/


}
