package dz.gouv.ppas.web.cagapps.security.filter;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.User;
import dz.gouv.ppas.web.cagapps.exceptions.AuthenticationException;
import dz.gouv.ppas.web.cagapps.security.services.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader(tokenHeader);
        if (requestTokenHeader != null)
            try {
                jwtService.getToken(requestTokenHeader).ifPresent(token -> {
                    jwtService.getSubFromToken(token).ifPresent(username -> {
                        if (SecurityContextHolder.getContext().getAuthentication() == null) {
                            User userDetails = (User) userDetailsService.loadUserByUsername(username);
                            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        }
                    });
                });
            } catch (IllegalArgumentException e) {
                throw new AuthenticationException("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                throw new AuthenticationException("JWT Token has expired");
            }
        filterChain.doFilter(request, response);
    }
}

