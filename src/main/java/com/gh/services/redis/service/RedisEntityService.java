package com.gh.services.redis.service;

import com.gh.services.redis.domain.RedisEntity;
import com.gh.services.redis.repository.RedisEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class RedisEntityService {

    private final RedisEntityRepository redisEntityRepository;

    public RedisEntityService(RedisEntityRepository redisEntityRepository) {
        this.redisEntityRepository = redisEntityRepository;
    }

    public RedisEntity get(String id) {
        return redisEntityRepository.findById(id).get();
    }

    public void save(RedisEntity re) {
        redisEntityRepository.save(re);
    }

}
