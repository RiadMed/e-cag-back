package dz.gouv.ppas.web.cagapps.security.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.request.JwtRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.JwtRequestToken;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.JwtResponse;

import java.security.NoSuchAlgorithmException;

public interface LoginService {

    JwtResponse<String> login(JwtRequest authenticationRequest) throws NoSuchAlgorithmException;

    JwtResponse<String> refresh(JwtRequestToken jwtRequestToken);
}
