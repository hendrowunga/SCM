package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication) {

        Principal principal = (Principal) authentication.getPrincipal();
        /* cara untuk mendapatkan email pengguna setelah pengguna berhasil login dengan menggunakan email dan password. */
        if (principal instanceof OAuth2AuthenticationToken) {
            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            if (clientId.equalsIgnoreCase("google")) {
                System.out.println("Getting email from google");

                // sign with google
            } else if (clientId.equalsIgnoreCase("github")) {
                System.out.println("Getting email from github");

                // sign with github
            }
            return "";

            // sign with facebook
        } else {
            System.out.println("Getting data from local database");
            return authentication.getName();
        }


    }

}
