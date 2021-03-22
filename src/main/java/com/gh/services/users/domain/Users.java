package com.gh.services.users.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@ToString(exclude = "accessHistory")
@Table(name="TB_USERS")
public class Users {
    @Id @GeneratedValue
    private Long id ;  // pk

    private String username;  // id
    private String password;  // pw

    private LocalDateTime updateDT;
    private LocalDateTime deleteDT;

    @OneToMany(mappedBy = "users")
    private List<AccessHistory> accessHistory ;
}
