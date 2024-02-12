package pl.kurs.exchange_api_micro.facade.auth;


import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {

    Authentication getAuthentication();
}
