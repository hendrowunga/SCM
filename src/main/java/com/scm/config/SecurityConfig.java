package com.scm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    //    User create and login using java code with in memory service
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withUsername("admin").password("admin123").build();

        var inMemoryUserDetailsManger = new InMemoryUserDetailsManager();
        return inMemoryUserDetailsManger;
    }

}
