package com.scm.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String userId;
    @Column(name = "user_name",nullable = false)
    private String name;
    private String email;
    private String password;
    @Column(length = 10000)
    private String about;
    @Column(length = 10000)
    private String profilePic;
    private String phoneNumber;
    // information
    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    // SELF, GOOGLE, FACEBOOK, TWITTER, LINKEDIN, GITHUB
    private Providers provider=Providers.SELF;
    private String providerUserId;



}
