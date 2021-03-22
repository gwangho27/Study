package com.gh.services.redis.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;


@RedisHash("RedisEntity")
public class RedisEntity implements Serializable {

    @Id
    private String id;

    private Long amount;
    private LocalDateTime refreshTime;

    @Builder
    public RedisEntity (String id, Long amount, LocalDateTime refreshTime) {
        this.id = id;
        this.amount =amount;
        this.refreshTime =refreshTime ;
    }

    public void refresh(long amount, LocalDateTime refreshTime) {
        if(refreshTime.isAfter(this.refreshTime)) {
            this.amount = amount;
            this.refreshTime = refreshTime;
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(LocalDateTime refreshTime) {
        this.refreshTime = refreshTime;
    }
}
