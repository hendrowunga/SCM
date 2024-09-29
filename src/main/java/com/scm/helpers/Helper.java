package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication) {

        /* cara untuk mendapatkan email pengguna setelah pengguna berhasil login dengan menggunakan email dan password. */
        if (authentication instanceof OAuth2AuthenticationToken) {
            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = "";


            if (clientId.equalsIgnoreCase("google")) {
                // sign with google
                System.out.println("Getting email from google");
                username = oauth2User.getAttribute("email").toString();
            } else if (clientId.equalsIgnoreCase("github")) {
                // sign with github
                System.out.println("Getting email from github");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString()
                        : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }
            return username;

            // sign with facebook
        } else {
            System.out.println("Getting data from local database");
            return authentication.getName();
        }


    }

}
