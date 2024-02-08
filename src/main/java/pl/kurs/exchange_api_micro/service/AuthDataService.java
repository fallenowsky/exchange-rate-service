package pl.kurs.exchange_api_micro.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class AuthDataService {

    private final ObjectMapper objectMapper;

    public String extractAuthData() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken) {
            return decodeToken(((JwtAuthenticationToken) auth).getToken().getTokenValue());
        }
        return null;
    }

    private String decodeToken(String token) {
        String tokenPayload = token.split("\\.")[1];
        String jsonPayload = new String(Base64.getDecoder().decode(tokenPayload), StandardCharsets.UTF_8);
        try {
            return objectMapper.readTree(jsonPayload).get("email").asText();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
