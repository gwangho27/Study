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

    @OneToMany(mappedBy = "users")
    private List<AccessHistory> accessHistory ;

    public Users () {}

    public Users (Long id ){
        this.id = id;
    }
}
