package com.gh.services.users.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString(exclude = "users")
@Table(name="TB_ACCESSHISTORY")
public class AccessHistory {

    @Id @GeneratedValue
    private Long id;

    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime accessTime;

    @ManyToOne
    @JoinColumn(name="USERS_ID")
    private Users users;
}
