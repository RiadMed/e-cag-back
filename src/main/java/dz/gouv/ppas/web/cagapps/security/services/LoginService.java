package dz.gouv.ppas.web.cagapps.security.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.request.JwtRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.JwtRequestToken;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.JwtResponse;

public interface LoginService {

    JwtResponse<String> login(JwtRequest authenticationRequest);

    JwtResponse<String> refresh(JwtRequestToken jwtRequestToken);
}
