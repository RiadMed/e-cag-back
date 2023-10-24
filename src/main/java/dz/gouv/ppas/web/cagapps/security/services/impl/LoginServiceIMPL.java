package dz.gouv.ppas.web.cagapps.security.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.AccessTokenDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.admin.CodeValidationMfaDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.JwtRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.JwtRequestToken;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.JwtResponse;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MailRequest;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.AccessToken;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.User;
import dz.gouv.ppas.web.cagapps.business.repositories.UserDao;
import dz.gouv.ppas.web.cagapps.business.services.AccessTokenServices;
import dz.gouv.ppas.web.cagapps.business.services.CodeValidationMfaServices;
import dz.gouv.ppas.web.cagapps.business.services.MailService;
import dz.gouv.ppas.web.cagapps.security.services.JwtService;
import dz.gouv.ppas.web.cagapps.security.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;

@AllArgsConstructor
@Service
@Transactional
public class LoginServiceIMPL implements LoginService {

    private final UserDao userDao;
    private final JwtService jwtService;
    private final MailService mailService;
    private final AccessTokenServices accessTokenServices;
    private final AuthenticationManager authenticationManager;
    private final CodeValidationMfaServices codeValidationMfaServices;

    @Override
    public JwtResponse<String> login(JwtRequest authenticationRequest) throws NoSuchAlgorithmException {
        JwtResponse response = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        if (response.isSuccess()) {
            Authentication auth = (Authentication) response.getToken();
            UserDetails principal = (UserDetails) auth.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(auth);
            Optional<User> user = userDao.findByAccountName(principal.getUsername());
            if (user.isPresent()) {
                // Suprimer les anciens token access
                accessTokenServices.deleteTokenByUser(user.get().getId());
                // Inserer un nouveau token access
                AccessTokenDto accessToken = accessTokenServices.save(new AccessTokenDto(UUID.randomUUID(), Instant.now().plusMillis(3600000), user.get().getId()));
                if (!user.get().isNouveauCompte()) {
                    codeValidationMfaServices.deleteCodeValidationByUserCode(user.get().getAccountName());
                    CodeValidationMfaDto codeValidation = codeValidationMfaServices.addNewCodeValidation(user.get().getAccountName());
                    List<String> mails = Arrays.asList(user.get().getEmail());
                    mailService.sendCodeDeValidation(new MailRequest(mails.toArray(new String[0])), codeValidation.getCodeValidation());
                }
                return new JwtResponse(accessToken.getIdAccess(), jwtService.generateToken(user.get()));
            }
        }
        return response;
    }

    @Override
    public JwtResponse<String> refresh(JwtRequestToken jwtRequestToken) {
        Optional<AccessTokenDto> accessTokenOpt = accessTokenServices.findById(jwtRequestToken.getIdAccess());
        if (accessTokenOpt.isPresent()) {
            if (accessTokenServices.verifyExpiration(accessTokenOpt.get())) {
                User user = userDao.getById(accessTokenOpt.get().getUserId());
                if (user != null) {
                    String token = jwtService.generateToken(user);
                    return new JwtResponse<>(accessTokenOpt.get().getIdAccess(), token);
                }
            } else {
                accessTokenServices.delete(accessTokenOpt.get());
                return new JwtResponse(false, "Access token expirer veuillez connecter à nouveau avec votre nom d'utilisateur et mot de passe");
            }
        }
        return new JwtResponse(false, "Access token non valide veuillez connecter à nouveau avec votre nom d'utilisateur et mot de passe");

    }


    private JwtResponse<Authentication> authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            return new JwtResponse(this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)));
        } catch (DisabledException e) {
            return new JwtResponse(false, "Cet utilisateur est bloqué veuillez contacté l'administrateur. (1002)");
        } catch (BadCredentialsException e) {
            return new JwtResponse(false, "Nom utilisateur ou mot de passe incorrect. (1001)");
        } catch (AccountExpiredException e) {
            return new JwtResponse(false, "Ce compte est expiré veuillez contacté l'administrateur. (1003)");
        } catch (InternalAuthenticationServiceException e) {
            return new JwtResponse(false, e.getMessage());
        }
    }


}
