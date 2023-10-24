package dz.gouv.ppas.web.cagapps.security.services.impl;

import dz.gouv.ppas.web.cagapps.security.model.RecaptchaResponse;
import dz.gouv.ppas.web.cagapps.security.services.CaptchaService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class CaptchaServiceIMPL implements CaptchaService {

    private final RestTemplate restTemplate;

    private static final String SECRET_KEY = "6LdSK5koAAAAAN9hTGLCXAJaUgE9_lYi_fIKQtbJ";
    private static final String SECRET_KEY_DEMO = "6LeX0L8oAAAAANaXzYdSgqIUWgHwEsUvw7IWAqHA";
    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public CaptchaServiceIMPL(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public RecaptchaResponse validateToken(String recaptchaToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret", SECRET_KEY_DEMO);
        map.add("response", recaptchaToken);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<RecaptchaResponse> response = restTemplate.exchange(VERIFY_URL,
                HttpMethod.POST,
                entity,
                RecaptchaResponse.class);
        return response.getBody();
    }
}
