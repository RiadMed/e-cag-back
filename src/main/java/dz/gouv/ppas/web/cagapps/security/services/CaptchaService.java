package dz.gouv.ppas.web.cagapps.security.services;

import dz.gouv.ppas.web.cagapps.security.model.RecaptchaResponse;

public interface CaptchaService {
    RecaptchaResponse validateToken(String recaptchaToken);

}
