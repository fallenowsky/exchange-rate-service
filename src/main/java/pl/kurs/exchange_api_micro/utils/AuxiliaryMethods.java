package pl.kurs.exchange_api_micro.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuxiliaryMethods {

    public String extractEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        JwtAuthenticationToken jwt = (JwtAuthenticationToken) auth;

        return jwt.getToken().getClaim("email");
    }
}
