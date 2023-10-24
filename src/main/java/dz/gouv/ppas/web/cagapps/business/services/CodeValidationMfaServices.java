package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.CodeValidationMfaDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MfaRequest;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.CodeValidationMfa;
import dz.restmapping.apps.services.GenericService;

import java.security.NoSuchAlgorithmException;

public interface CodeValidationMfaServices extends GenericService<CodeValidationMfa, CodeValidationMfaDto, Long> {

    CodeValidationMfaDto getLastCodeValidationByUserCode(String usercode);

    CodeValidationMfaDto addNewCodeValidation(String usercode) throws NoSuchAlgorithmException;

    EntityResponse<CodeValidationMfaDto> validateCodeMfa(MfaRequest mfaRequest);

    void deleteCodeValidationByUserCode(String userCode);

    EntityResponse<CodeValidationMfaDto> demanderUnNouveauCode(MfaRequest mfaRequest) throws NoSuchAlgorithmException;
}
