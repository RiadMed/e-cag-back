package dz.gouv.ppas.web.cagapps.security.config;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.User;
import dz.gouv.ppas.web.cagapps.exceptions.AuthenticationException;
import dz.gouv.ppas.web.cagapps.tools.AppsUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

@Component
public class TokenUtils implements Serializable {

    private static KeyStore keyStore;
    private static Clock clock = DefaultClock.INSTANCE;

    private static final String prefix = "Bearer ";
    private static final String secret = "access@djezzy.dz";
    private static final String domain = "djezzy.dz";
    private static final Long expiration = 600000L;

    @PostConstruct
    public void init() throws AuthenticationException {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/djezzy_access.jks");
            keyStore.load(resourceAsStream, secret.toCharArray());
        } catch (CertificateException | NoSuchAlgorithmException | IOException | KeyStoreException e) {
            throw new AuthenticationException("Exception occurred while loading keystore");
        }
    }

    public static String doGenerateToken(User user) {
        final Date createdDate = clock.now();
        return Jwts.builder()
                .setSubject(user.getAccountName())
                .claim("username",
                        user.getAccountName())
                .claim("enabled",
                        user.isEnabled())
                .setIssuedAt(createdDate)
                // .setExpiration(AppUtils.calculateExpirationDate(createdDate, user.getAccountExpires()))
                .setExpiration(AppsUtils.calculateExpirationDate(clock.now(), expiration.intValue()))
                .signWith(getPrivateKey())
                .compact();
    }

    public static String refreshToken(String token) throws AuthenticationException {
        final Claims claims = getAllClaimsFromToken(token.substring(prefix.length()));
        Function<Claims, String> claimsSubject = Claims::getSubject;
        return Jwts.builder()
                .setSubject(claimsSubject.apply(claims))
                .claim("username",
                        String.valueOf(claims.get("username")))
                .claim("title",
                        String.valueOf(claims.get("title")))
                .claim("enabled",
                        String.valueOf(claims.get("enabled")))
                .setIssuedAt(clock.now())
                // .setExpiration(AppUtils.calculateExpirationDate(createdDate, user.getAccountExpires()))
                .setExpiration(AppsUtils.calculateExpirationDate(clock.now(), expiration.intValue()))
                .signWith(getPrivateKey())
                .compact();
    }

    public static <T> Optional<T> getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws AuthenticationException {
        final Claims claims = getAllClaimsFromToken(token);
        return Optional.of(claimsResolver.apply(claims));
    }

    public static Claims getAllClaimsFromToken(String token) throws AuthenticationException {
        return Jwts.parser().setSigningKey(getPublickey()).parseClaimsJws(token).getBody();
    }

    public static Optional<Date> getExpirationDateFromToken(String token) throws AuthenticationException {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public static Boolean isTokenExpired(String token) throws AuthenticationException {
        Optional<Date> expiration = TokenUtils.getExpirationDateFromToken(token);
        return expiration.isPresent() ? expiration.get().before(clock.now()) : true;
    }

    public static Boolean isValidateToken(String requestHeader) {
        return requestHeader.startsWith(prefix);
    }

    public static Optional<String> getToken(String requestHeader) {
        return isValidateToken(requestHeader) ? Optional.of(requestHeader.substring(prefix.length())) : Optional.empty();
    }

    private static PrivateKey getPrivateKey() throws AuthenticationException {
        try {
            return (PrivateKey) keyStore.getKey(domain, secret.toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new AuthenticationException("Exception occured while retrieving public key from keystore");
        }
    }

    public static PublicKey getPublickey() throws AuthenticationException {
        try {
            return keyStore.getCertificate(domain).getPublicKey();
        } catch (KeyStoreException e) {
            throw new AuthenticationException("Exception occured while " +
                    "retrieving public key from keystore");
        }
    }

}
