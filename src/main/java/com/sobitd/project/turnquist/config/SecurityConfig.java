package com.sobitd.project.turnquist.config;


import com.sobitd.project.turnquist.entity.UserAccount;
import com.sobitd.project.turnquist.repository.UserRepository;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.http.HttpMethod;


import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


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

    @Bean
    SecurityFilterChain configureSecurity(HttpSecurity http)
    throws Exception {
        http.authorizeHttpRequests()
            .requestMatchers("/login").permitAll()
            .requestMatchers("/", "/search").authenticated()
            .requestMatchers(HttpMethod.GET, "/api/**")
            .authenticated()
            .requestMatchers(HttpMethod.POST, "/new-video","/api/**").hasRole("ADMIN")
            .anyRequest().denyAll()
            .and()
            .formLogin()
            .and()
            .httpBasic();
        return http.build();
    }
}

