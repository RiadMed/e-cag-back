package dz.gouv.ppas.web.cagapps.security.services;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.User;

import java.util.Optional;

public interface JwtService {

    Optional<String> getSubFromToken(String token);

    Boolean validateToken(String requestHeader);

    Boolean isTokenExpired(String token);

    String generateToken(User user);

    Optional<String> getToken(String requestHeader);

    String refreshToken(String token);
}
