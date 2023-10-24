package dz.gouv.ppas.web.cagapps.security.captcha;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AppReCaptchaV3 {

}