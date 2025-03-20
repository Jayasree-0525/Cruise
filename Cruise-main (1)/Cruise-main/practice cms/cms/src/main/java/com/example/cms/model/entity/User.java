package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")

public class User {
    @Id
    @NotEmpty
    private String username;

    @NotNull
    private String password;

    @NotNull
    private Boolean isAdmin=false;

    public User(String username, String rawPwd){
        this.username = username;
        this.password = rawPwd;
        this.isAdmin = false;
    }

}
