package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.CodeValidationMfaDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MfaRequest;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.CodeValidationMfa;
import dz.gouv.ppas.web.cagapps.business.services.CodeValidationMfaServices;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.CODE_VALIDATION_ROOT)
public class CodeValidationMfaController extends GenericRestController<CodeValidationMfaServices, CodeValidationMfa, CodeValidationMfaDto, Long> {

    private final CodeValidationMfaServices codeValidationMfaServices;

    @PostMapping("/validateCode")
    public EntityResponse<CodeValidationMfaDto> validateCodeMfa(@RequestBody MfaRequest mfaRequest) {
        return codeValidationMfaServices.validateCodeMfa(mfaRequest);
    }

    @PostMapping("/nouveauCode")
    public EntityResponse<CodeValidationMfaDto> demanderUnNouveauCode(@RequestBody MfaRequest mfaRequest) throws NoSuchAlgorithmException {
        return codeValidationMfaServices.demanderUnNouveauCode(mfaRequest);
    }
}
