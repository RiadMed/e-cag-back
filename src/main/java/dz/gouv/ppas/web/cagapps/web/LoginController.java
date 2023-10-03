package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.request.*;
import dz.gouv.ppas.web.cagapps.business.services.UserService;
import dz.gouv.ppas.web.cagapps.security.services.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@AllArgsConstructor
@RestController
public class LoginController {

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping(value = "/login")
    public JwtResponse<String> login(@RequestBody JwtRequest authenticationRequest) {
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


}
