package com.sobitd.project.turnquist.config;


import com.sobitd.project.turnquist.entity.UserAccount;
import com.sobitd.project.turnquist.repository.UserRepository;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;


@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userService(UserRepository repo) {
        return username -> repo.findByUsername(username)
        .map(UserAccount::asUser)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Bean
    public CommandLineRunner initUsers(UserRepository repository) {
        return args -> {
            repository.save(new UserAccount("user", "password","ROLE_USER"));
            repository.save(new UserAccount("admin", "password","ROLE_ADMIN"));
        };
    }
}

