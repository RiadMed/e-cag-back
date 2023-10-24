package dz.gouv.ppas.web.cagapps.business.data.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class JwtRequest implements Serializable {

    private String username;
    private String password;
    private String tokenReCaptcha;

    public JwtRequest(String username, String password, String tokenReCaptcha) {
        this.setUsername(username);
        this.setPassword(password);
        this.setTokenReCaptcha(tokenReCaptcha);
    }

}
