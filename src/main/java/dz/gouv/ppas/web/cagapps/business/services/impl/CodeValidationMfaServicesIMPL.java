package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.CodeValidationMfaDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MailRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MfaRequest;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.CodeValidationMfa;
import dz.gouv.ppas.web.cagapps.business.repositories.CodeValidationMfaDao;
import dz.gouv.ppas.web.cagapps.business.services.CodeValidationMfaServices;
import dz.gouv.ppas.web.cagapps.business.services.MailService;
import dz.gouv.ppas.web.cagapps.tools.AppsUtils;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class CodeValidationMfaServicesIMPL extends GenericServiceImpl<CodeValidationMfaDao, CodeValidationMfa, CodeValidationMfaDto, Long> implements CodeValidationMfaServices {

    private final MailService mailService;
    private final CodeValidationMfaDao codeValidationMfaDao;

    @Override
    public CodeValidationMfaDto getLastCodeValidationByUserCode(String usercode) {
        return mapToDto(codeValidationMfaDao.getLastCodeValidationByUserCode(usercode));
    }


    @Override
    public CodeValidationMfaDto addNewCodeValidation(String usercode) throws NoSuchAlgorithmException {
        CodeValidationMfaDto currentCode = getLastCodeValidationByUserCode(usercode);
        int currentNumSeq = 1;
        if (currentCode != null) {
            currentNumSeq = currentCode.getNumeroSeq() + 1;
        }
        CodeValidationMfaDto newCode = new CodeValidationMfaDto();
        newCode.setUsercode(AppsUtils.getUserPrincipal().getAccountName());
        newCode.setNumeroSeq(currentNumSeq);
        newCode.setNbTentative(0);
        newCode.setStatus(1);
        newCode.setCodeValidation(generateCode(6));
        newCode.setDate(LocalDateTime.now());
        return save(newCode);
    }

    @Override
    public EntityResponse<CodeValidationMfaDto> validateCodeMfa(MfaRequest mfaRequest) {
        if (AppsUtils.nullCheck(mfaRequest.getCodeValidation())) {
            return new EntityResponse<>("le code validation est obligatoire.");
        }
        if (mfaRequest.getCodeValidation().length() != 6) {
            return new EntityResponse<>("La taille du code de validation est incorrecte.");
        }

        if (!mfaRequest.getUserCode().equals(AppsUtils.getUserPrincipal().getAccountName())) {
            return new EntityResponse<>("Le code d'utilisateur est incorrecte.");
        }

        CodeValidationMfaDto lastCodeDeValidation = getLastCodeValidationByUserCode(mfaRequest.getUserCode());

        if (lastCodeDeValidation == null) {
            return new EntityResponse<>("Aucun code de validation est enregister dans la base, veuillez demander un nouveau code svp.");
        }

        if (lastCodeDeValidation.getStatus() == 2 || lastCodeDeValidation.getStatus() == 0) {
            lastCodeDeValidation.setNbTentative(lastCodeDeValidation.getNbTentative() + 1);
            save(lastCodeDeValidation);
            return new EntityResponse<>(new CodeValidationMfaDto(lastCodeDeValidation.getNbTentative()), false, "Ce code de validation est invalide ou désactivé, veuillez demander un nouveau code svp.");
        }

        if (lastCodeDeValidation.getNbTentative() >= 3) {
            lastCodeDeValidation.setStatus(0);
            save(lastCodeDeValidation);
            return new EntityResponse<>(new CodeValidationMfaDto(lastCodeDeValidation.getNbTentative()), false, "Vous avez dépassé le nembre maximum des tentative, veuillez demander un nouveau code svp.");
        }

        if (!mfaRequest.getCodeValidation().equals(lastCodeDeValidation.getCodeValidation())) {
            lastCodeDeValidation.setNbTentative(lastCodeDeValidation.getNbTentative() + 1);
            save(lastCodeDeValidation);
            return new EntityResponse<>(new CodeValidationMfaDto(lastCodeDeValidation.getNbTentative()), false, "Ce code de validation est incorrecte, veuillez demander un nouveau code svp.");
        }


        if (lastCodeDeValidation.getDate().plusHours(8).isBefore(LocalDateTime.now())) {
            lastCodeDeValidation.setStatus(0);
            lastCodeDeValidation.setNbTentative(3);
            save(lastCodeDeValidation);
            return new EntityResponse<>(new CodeValidationMfaDto(lastCodeDeValidation.getNbTentative()), false, "Ce Code de validation est expiré, veuillez demander un nouveau code svp.");
        }

        lastCodeDeValidation.setStatus(2);
        CodeValidationMfaDto saved = save(lastCodeDeValidation);
        return new EntityResponse<>(saved);
    }

    @Override
    public void deleteCodeValidationByUserCode(String userCode) {
        codeValidationMfaDao.deleteCodeValidationByUserCode(userCode);
    }

    @Override
    public EntityResponse<CodeValidationMfaDto> demanderUnNouveauCode(MfaRequest mfaRequest) throws NoSuchAlgorithmException {
        if (AppsUtils.nullCheck(mfaRequest.getUserCode())) {
            return new EntityResponse<>("Le code de l'utilisateur est obligatoire.");
        }
        if (!mfaRequest.getUserCode().equals(AppsUtils.getUserPrincipal().getAccountName())) {
            return new EntityResponse<>("Le code d'utilisateur est incorrecte.");
        }

        CodeValidationMfaDto codeValidation = addNewCodeValidation(mfaRequest.getUserCode());
        List<String> mails = Arrays.asList(AppsUtils.getUserPrincipal().getEmail());
        mailService.sendCodeDeValidation(new MailRequest(mails.toArray(new String[0])), codeValidation.getCodeValidation());
        return new EntityResponse<>(true, "Code de validation envoyé avec succès, veuillez vérifier votre boite de récéption.");
    }

    private String generateCode(int size) throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        int num = random.nextInt(Integer.parseInt(StringUtils.repeat("9", size)));
        String code = String.format("%0" + size + "d", num);
        return code;
    }
}
