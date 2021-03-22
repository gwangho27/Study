package com.gh.services.redis.repository;

import com.gh.services.redis.domain.RedisEntity;
import org.springframework.data.repository.CrudRepository;

public interface RedisEntityRepository extends CrudRepository<RedisEntity,String> {

}
