package com.scm.config;

import com.scm.services.impl.SecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private SecurityCustomUserDetailService userDetailService;


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        // configuration
        // Urls dikonfigurasi agar mengetahui mana yang akan dipublikasikan dan mana yang akan diprivasi
        httpSecurity.authorizeHttpRequests(
                authorization->{
//                    authorization.requestMatchers("/home","/register","services").permitAll();
                      authorization.requestMatchers("/user/**").authenticated();
                      authorization.anyRequest()
                              .permitAll();
        });
//        Ini Membuat perubahan pada form login, maka kw modifikasi harus dilakukan di area kode ini.

        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

// user create and login using java code with in memory service

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("admin123")
//                .password("admin123")
//                .roles("ADMIN", "USER")
//                .build();
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("user123")
//                .password("password")
//                // .roles(null)
//                .build();
//
//        var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,
//                user2);
//        return inMemoryUserDetailsManager;
//
//    }
