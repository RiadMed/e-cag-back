package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.request.*;
import dz.gouv.ppas.web.cagapps.business.services.UserService;
import dz.gouv.ppas.web.cagapps.security.captcha.AppReCaptchaV3;
import dz.gouv.ppas.web.cagapps.security.services.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@Log4j2
@AllArgsConstructor
@RestController
public class LoginController {

    private final UserService userService;
    private final LoginService loginService;

    @AppReCaptchaV3
    @PostMapping(value = "/login")
    public JwtResponse<String> login(@RequestBody JwtRequest authenticationRequest) throws NoSuchAlgorithmException {
        return loginService.login(authenticationRequest);
    }

    @PostMapping(value = "/refresh")
    public JwtResponse<String> refresh(@RequestBody JwtRequestToken jwtRequestToken) {
        return loginService.refresh(jwtRequestToken);
    }

    @PostMapping("/sendPassword")
    public MessageResponse sendPassword(@RequestBody MessageRequest messageRequest) {
        return userService.sendPassword(messageRequest);
    }

    @GetMapping("/info")
    public String getAppInfo(){
        return "Welcome to E-CAG Api";
    }


}
