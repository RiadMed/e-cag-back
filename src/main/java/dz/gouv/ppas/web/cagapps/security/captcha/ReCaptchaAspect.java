package dz.gouv.ppas.web.cagapps.security.captcha;


import dz.gouv.ppas.web.cagapps.business.data.dto.request.JwtRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.JwtResponse;
import dz.gouv.ppas.web.cagapps.security.model.RecaptchaResponse;
import dz.gouv.ppas.web.cagapps.security.services.CaptchaService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ReCaptchaAspect {

    @Autowired
    private CaptchaService captchaService;

    @Around("@annotation(dz.gouv.ppas.web.cagapps.security.captcha.AppReCaptchaV3)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String action = method.getName();
        for (Object ob : joinPoint.getArgs()) {
            if (ob instanceof JwtRequest) {
                JwtRequest request = (JwtRequest) ob;

                String captchaTokenResponse = request.getTokenReCaptcha();
                if (captchaTokenResponse == null) {
                    return new JwtResponse<>("ReCaptcha Message Error Action(" + action + ") - le Token est null");
                }

                RecaptchaResponse captchaApiResponse = checkReCaptchaResponse(captchaTokenResponse);

                if (captchaApiResponse == null) {
                    return new JwtResponse<>("ReCaptcha Message Error - le retour du reCaptcha est null");
                }

                if (!captchaApiResponse.getSuccess()) {
                    return new JwtResponse<>("ReCaptcha Message Error Action(" + action + ") - isSuccess(" + captchaApiResponse.getSuccess() + ") - " + " Score(" + captchaApiResponse.getScore() + ")");
                }

                if (captchaApiResponse.getScore() < 0.3) {
                    return new JwtResponse<>("ReCaptcha Message Error Action(" + action + ") - isSuccess(" + captchaApiResponse.getSuccess() + ") - " + " Score(" + captchaApiResponse.getScore() + ")");
                }
            }
        }
        return joinPoint.proceed();
    }

    private RecaptchaResponse checkReCaptchaResponse(String captchaTokenResponse) {
        return captchaService.validateToken(captchaTokenResponse);
    }


}

