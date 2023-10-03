package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.UserDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.*;
import dz.gouv.ppas.web.cagapps.business.data.dto.enums.StatusEnum;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.FileRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MailRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.NextStatusRequest;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAG;
import dz.gouv.ppas.web.cagapps.business.repositories.SessionCAGDao;
import dz.gouv.ppas.web.cagapps.business.services.*;
import dz.gouv.ppas.web.cagapps.tools.AppsUtils;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
@Transactional
public class SessionCAGServicesIMPL extends GenericServiceImpl<SessionCAGDao, SessionCAG, SessionCAGDto, Long> implements SessionCAGServices {

    private final MailService mailService;
    private final UserService userService;
    private final StatusServices statusServices;
    private final SessionCAGDao sessionCAGDao;
    private final SessionStatusServices sessionStatusServices;
    private final OrganisationServices organisationServices;

    private static String PV_UPLOAD_FOLDER = "E:\\files\\PV\\";

    @Override
    public EntityResponse<SessionCAGDto> saveSesseionCAG(SessionCAGDto entity) {
        if (null == entity.getCode()) {
            return new EntityResponse<>("Le code de la session CAG est un champ obligatoire. (5001)");
        }
        Optional<SessionCAGDto> sessionCAGOpt = findSessionCAGByCode(entity.getCode());
        if (sessionCAGOpt.isPresent()) {
            return new EntityResponse<>("Le code " + entity.getCode() + " existe déja. (5001)");
        }

        StatusDto status = statusServices.findStatusByLabel(StatusEnum.PLANIFIER.toString());
        if (status == null) {
            return new EntityResponse<>("Status " + StatusEnum.PLANIFIER.toString() + " non trouvée!.");
        }

        entity.setStatusId(status.getId());
        entity.setStatusLabel(status.getLabel());
        entity.setChangementStatusDate(LocalDateTime.now());
        entity.setCreationDate(LocalDateTime.now());
        entity.setCreerPar(AppsUtils.getUserPrincipal().getAccountName());
        SessionCAGDto saved = save(entity);

        Optional<OrganisationDto> organisationDto = organisationServices.findById(saved.getOrganisationId());

        Optional<UserDto> user = userService.findUsersByEmail(organisationDto.get().getSecretaireMail());
        if (!user.isPresent()) {
            return new EntityResponse<>("Aucun secretaire trouvé pour l'organisation " + organisationDto.get().getCode() + ". Mail non envoyé. (6001)");
        }

        if (saved != null) {
            mailService.sendMailDePlannification(new MailRequest(), user.get().getFullName(), organisationDto.get(), entity.getSessionDateTime());
            sessionStatusServices.save(new SessionStatusDto(status.getId(), saved.getId(), user.get().getId()));
            return new EntityResponse<>(saved);
        }
        return new EntityResponse<>("Erreur planification non enregister. (6002)");
    }

    @Override
    public EntityResponse<SessionCAGDto> annulerLaSessionPlannifier(Long id) {
        Optional<SessionCAGDto> dtoOptional = findById(id);
        if (!dtoOptional.isPresent()) {
            return new EntityResponse<>("Session CAG N: " + id + " non trouvée!. (6101)");
        }
        Optional<UserDto> user = userService.findByAccountName(AppsUtils.getUserPrincipal().getAccountName());
        if (!user.isPresent()) {
            return new EntityResponse<>("User Principal non trouvée!. (6102)");
        }

        StatusDto status = statusServices.findStatusByLabel(StatusEnum.DATE_ANNULER.toString());
        SessionCAGDto session = dtoOptional.get();
        session.setStatusId(status.getId());
        session.setStatusLabel(status.getLabel());
        session.setChangementStatusDate(LocalDateTime.now());
        sessionStatusServices.save(new SessionStatusDto(status.getId(), session.getId(), user.get().getId()));
        SessionCAG cag = sessionCAGDao.save(mapToObject(session));
        return new EntityResponse<>(mapToDto(cag));
    }

    @Override
    public Optional<SessionCAGDto> findSessionCAGByCode(String code) {
        return sessionCAGDao.findSessionCAGByCode(code, Arrays.asList(StatusEnum.DATE_ANNULER.toString(), StatusEnum.ANNULER.toString())).map(x -> mapToDto(x));
    }

    @Override
    public EntityResponse<SessionCAGDto> addPVFile(FileRequest file) throws IOException {
        if (file == null) {
            return new EntityResponse<>("Le fichier PDF est obligatoir. (7001)");
        }

        Optional<SessionCAGDto> sessionOpt = findById(file.getId());
        if (!sessionOpt.isPresent()) {
            return new EntityResponse<>("Erreur dans l'enregistrement du file. (7001)");
        }
        SessionCAGDto sessionCAGDto = sessionOpt.get();
        createDirIfNotExist(sessionOpt.get().getId());
        String fileString64 = file.getFile().replace("data:application/pdf;base64,", "");
        byte[] bytes = Base64.getDecoder().decode(fileString64);
        Path path = Paths.get((PV_UPLOAD_FOLDER) + "\\" + sessionCAGDto.getId() + "\\" + file.getName());
        sessionCAGDto.setFilePath((PV_UPLOAD_FOLDER) + sessionCAGDto.getId() + "\\" + file.getName());
        sessionCAGDto.setPvDate(LocalDateTime.now());
        sessionCAGDto.setPvAjouterPar(AppsUtils.getUserPrincipal().getAccountName());
        Files.write(path, bytes);

        StatusDto status = statusServices.findStatusByLabel(StatusEnum.PV_FILE_ADD.toString());
        if (status == null) {
            return new EntityResponse<>("Status " + StatusEnum.PV_FILE_ADD.toString() + " non trouvée!.");
        }

        sessionCAGDto.setStatusId(status.getId());
        sessionCAGDto.setStatusLabel(status.getLabel());
        sessionCAGDto.setChangementStatusDate(LocalDateTime.now());
        SessionCAGDto saved = save(sessionCAGDto);
        if (saved != null) {
            sessionStatusServices.save(new SessionStatusDto(status.getId(), saved.getId(), AppsUtils.getUserPrincipal().getId()));
            return new EntityResponse<>(save(sessionCAGDto), "PV Ajouter avec succés!.");
        }
        return new EntityResponse<>("Erreur dans l'enregistrement du file. (7001)");
    }

    @Override
    public String getFiles(Long idFile) {
        Optional<SessionCAGDto> dtoOpt = findById(idFile);
        if (dtoOpt.isPresent()) {
            try {
                return "data:application/pdf;base64," + AppsUtils.asString(AppsUtils.getResources(dtoOpt.get().getFilePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public EntityResponse<SessionCAGDto> goToNextStatus(NextStatusRequest nextStatusRequest) {

        Optional<SessionCAGDto> sessionOpt = findById(nextStatusRequest.getSessionId());
        if (!sessionOpt.isPresent()) {
            return new EntityResponse<>("Erreur dans le changement du statut de la session. (6101)");
        }

        StatusDto status = statusServices.findStatusByLabel(nextStatusRequest.getStatusLabel());
        if (status == null) {
            return new EntityResponse<>("Status " + nextStatusRequest.getStatusLabel() + " non trouvée!. (6101)");
        }

        SessionCAGDto sessionCAGDto = sessionOpt.get();
        sessionCAGDto.setStatusId(status.getId());
        sessionCAGDto.setStatusLabel(status.getLabel());
        sessionCAGDto.setChangementStatusDate(LocalDateTime.now());
        SessionCAGDto saved = save(sessionCAGDto);
        if (saved != null) {
            sessionStatusServices.save(new SessionStatusDto(status.getId(), saved.getId(), AppsUtils.getUserPrincipal().getId()));
            return new EntityResponse<>(save(sessionCAGDto), "PV Ajouter avec succés!.");
        }
        return new EntityResponse<>("Erreur dans le changement du statut de la session. (6101)");
    }

    @Override
    public EntityResponse<SessionCAGDto> demarrerLaReunion(SessionCAGDto sessionCAGDto) {
        StatusDto status = statusServices.findStatusByLabel(StatusEnum.METTING_STARTED.toString());
        if (status == null) {
            return new EntityResponse<>("Erreur dans le déammarrage de la réunion CAG (Statut). (7004)");
        }
        Optional<UserDto> user = userService.findByAccountName(AppsUtils.getUserPrincipal().getAccountName());
        if (!user.isPresent()) {
            return new EntityResponse<>("Erreur dans le déammarrage de la réunion CAG (User). (7004)");
        }

        if (StatusEnum.METTING_STARTED.toString().equals(sessionCAGDto.getInvitationsList())) {
            return new EntityResponse<>("Cette réunion est déja démarrées. (7005)");
        }
        sessionCAGDto.setStatusId(status.getId());
        sessionCAGDto.setStatusLabel(status.getLabel());
        SessionCAGDto saved = save(sessionCAGDto);
        if (saved != null) {
            sessionStatusServices.save(new SessionStatusDto(status.getId(), sessionCAGDto.getId(), user.get().getId()));
            return new EntityResponse<>(saved);
        }

        return new EntityResponse<>("Erreur dans le déammarrage de la réunion CAG. (7004)");
    }

    private void createDirIfNotExist(Long sessionCAGId) {
        File directory = new File((PV_UPLOAD_FOLDER) + sessionCAGId + "\\");
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}
