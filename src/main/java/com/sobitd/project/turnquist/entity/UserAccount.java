package com.sobitd.project.turnquist.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorities = new ArrayList<>();

    public UserAccount() {}

    public UserAccount(String username, String password, String... roles) {
        this.username = username;
        this.password = password;
        this.authorities = List.of(roles);
    }

    public UserDetails asUser() {
        return User.withDefaultPasswordEncoder()
            .username(getUsername())
            .password(getPassword())
            .authorities(getAuthorities().toArray(new String[0]))
            .build();
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}

