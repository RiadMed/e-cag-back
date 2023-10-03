package dz.gouv.ppas.web.cagapps.security.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.User;
import dz.gouv.ppas.web.cagapps.security.config.TokenUtils;
import dz.gouv.ppas.web.cagapps.security.services.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtServiceIMPL implements JwtService {

    @Override
    public Optional<String> getSubFromToken(String token) {
        return TokenUtils.getClaimFromToken(token, Claims::getSubject);
    }

    @Override
    public Boolean validateToken(String requestHeader) {
        return TokenUtils.isValidateToken(requestHeader);
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return TokenUtils.isTokenExpired(token);
    }

    @Override
    public String generateToken(User user) {
        return TokenUtils.doGenerateToken(user);
    }

    @Override
    public Optional<String> getToken(String requestHeader) {
        return TokenUtils.getToken(requestHeader);
    }

       @Override
    public String refreshToken(String token) {
        return TokenUtils.refreshToken(token);
    }

}
