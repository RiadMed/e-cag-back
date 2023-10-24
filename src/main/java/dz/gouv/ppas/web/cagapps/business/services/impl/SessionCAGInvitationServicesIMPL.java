package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.UserDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.*;
import dz.gouv.ppas.web.cagapps.business.data.dto.enums.StatusEnum;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.MailRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.statistic.InvitationStatistic;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGInvitation;
import dz.gouv.ppas.web.cagapps.business.repositories.SessionCAGInvitationDao;
import dz.gouv.ppas.web.cagapps.business.services.*;
import dz.gouv.ppas.web.cagapps.tools.AppsUtils;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SessionCAGInvitationServicesIMPL extends GenericServiceImpl<SessionCAGInvitationDao, SessionCAGInvitation, SessionCAGInvitationDto, Long> implements SessionCAGInvitationServices {

    private final MailService mailService;
    private final UserService userService;
    private final StatusServices statusServices;
    private final SessionCAGServices sessionCAGServices;
    private final OrganisationServices organisationServices;
    private final SessionStatusServices sessionStatusServices;
    private final SessionCAGInvitationDao sessionCAGInvitationDao;

    @Override
    public EntityResponse<List<SessionCAGInvitationDto>> saveAndSendInvitations(List<SessionCAGInvitationDto> list) {
        if (list.isEmpty()) {
            return new EntityResponse<>("Aucun membre est selectioné. (7001)");
        }

        Optional<SessionCAGDto> sessionCAGOpt = sessionCAGServices.findById(list.get(0).getSessionCAGId());
        if (!sessionCAGOpt.isPresent()) {
            return new EntityResponse<>("Erreur dans l'envoi des invitation CAG. (7002)");
        }
        SessionCAGDto sessionCAG = sessionCAGOpt.get();
        if (StatusEnum.INVITATION_SEND.toString().equals(sessionCAG.getInvitationsList())) {
            return new EntityResponse<>("Les invitations sont déja envoyées pour cette réunion. (7005)");
        }
        Optional<OrganisationDto> organisation = organisationServices.findById(sessionCAG.getOrganisationId());
        if (!organisation.isPresent()) {
            return new EntityResponse<>("Erreur dans l'envoi des invitation CAG (Organisation). (7002)");
        }
        StatusDto status = statusServices.findStatusByLabel(StatusEnum.INVITATION_SEND.toString());
        if (status == null) {
            return new EntityResponse<>("Erreur dans l'envoi des invitation CAG (Statut). (7002)");
        }
        Optional<UserDto> user = userService.findByAccountName(AppsUtils.getUserPrincipal().getAccountName());
        if (!user.isPresent()) {
            return new EntityResponse<>("Erreur dans l'envoi des invitation CAG (User). (7002)");
        }

        List<String> mails = list.stream().map(x -> x.getMail()).collect(Collectors.toList());
        mailService.sendMailInvitations(new MailRequest(mails.toArray(new String[0])), organisation.get(), sessionCAG.getSessionDateTime(), sessionCAG.getAdresse());

        sessionCAG.setStatusId(status.getId());
        sessionCAG.setStatusLabel(status.getLabel());
        sessionCAGServices.save(sessionCAG);
        sessionStatusServices.save(new SessionStatusDto(status.getId(), sessionCAG.getId(), user.get().getId()));
        list.stream().forEach(x -> {
            x.setSessionCAGId(sessionCAG.getId());
            x.setDateInvitation(LocalDateTime.now());
        });
        return new EntityResponse<>((List<SessionCAGInvitationDto>) saveAll(list));
    }

    @Override
    public List<SessionCAGInvitationDto> findSessionCAGInvitationByMail(String mail) {
        return mapToDto(sessionCAGInvitationDao.findSessionCAGInvitationByMailAndInvitationStatus(mail, false));
    }

    @Override
    public EntityResponse<SessionCAGInvitationDto> changeStatus(Long id) {
        Optional<SessionCAGInvitation> invitationOpt = sessionCAGInvitationDao.findById(id);
        if (!invitationOpt.isPresent()) {
            return new EntityResponse<>("Invitation non trouvée. (7003)");
        }
        if (invitationOpt.get().getInvitationStatus()) {
            return new EntityResponse<>(true, "");
        }
        SessionCAGInvitation cagInvitationDto = invitationOpt.get();
        cagInvitationDto.setInvitationStatus(true);
        cagInvitationDto.setInvitationStatusDate(LocalDateTime.now());
        SessionCAGInvitation saved = sessionCAGInvitationDao.save(cagInvitationDto);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:MM");
        if (saved != null)
            return new EntityResponse<>(SessionCAGInvitationDto.builder().id(saved.getId())
                    .dateInvitation(saved.getDateInvitation())
                    .mail(saved.getMail())
                    .membre(saved.getMembre())
                    .invitationStatus(saved.getInvitationStatus())
                    .invitationStatusDate(saved.getInvitationStatusDate())
                    .sessionCAGId(saved.getSessionCAG().getId())
                    .sessionCAGCode(saved.getSessionCAG().getCode())
                    .sessionCAGAdresse(saved.getSessionCAG().getAdresse())
                    .sessionCAGTypeSession(saved.getSessionCAG().getTypeSession())
                    .build(), "Invitation lu!.");
        return new EntityResponse<>("Erreur dans le changement du statut de l'invitation CAG. (7003)");
    }

    @Override
    public EntityResponse<SessionCAGInvitationDto> checkPresences(SessionCAGInvitationDto sessionCAGInvitationDto) {
        SessionCAGInvitationDto saved = setPresence(sessionCAGInvitationDto);
        if (saved != null) {
            return new EntityResponse<>(saved);
        }
        return new EntityResponse<>("Erreur la liste de présence. (7003)");
    }

    @Override
    public List<SessionCAGInvitationDto> checkPresencesAll(SessionCAGDto sessionCAGDto) {
        if (sessionCAGDto.getInvitationsList().isEmpty()) {
            return null;
        }
        sessionCAGDto.getInvitationsList().stream().forEach(x -> {
            x.setPresent(true);
            setPresence(x);
        });
        return sessionCAGDto.getInvitationsList();
    }

    @Override
    public List<SessionCAGInvitationDto> checkAbsenceAll(SessionCAGDto sessionCAGDto) {
        if (sessionCAGDto.getInvitationsList().isEmpty()) {
            return null;
        }
        sessionCAGDto.getInvitationsList().stream().forEach(x -> {
            x.setPresent(false);
            setPresence(x);
        });
        return sessionCAGDto.getInvitationsList();
    }

    @Override
    public InvitationStatistic checkInvitations(Integer organisationId) {
        List<String> listStatus = Arrays.asList(StatusEnum.PLANIFIER.toString(), StatusEnum.ANNULER.toString(), StatusEnum.PV_VALIDER.toString());
        List<SessionCAGInvitationDto> list = mapToDto(sessionCAGInvitationDao.findByOrgIdAndStatus(organisationId, listStatus));
        int lu = list.stream().filter(x -> x.getInvitationStatus()).collect(Collectors.toList()).size();
        return new InvitationStatistic(lu, list.size() - lu);
    }

    private SessionCAGInvitationDto setPresence(SessionCAGInvitationDto sessionCAGInvitationDto) {
        if (sessionCAGInvitationDto == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:MM");
        LocalDateTime date = sessionCAGInvitationDto.getDateInvitation() != null ? sessionCAGInvitationDto.getDateInvitation() : LocalDateTime.now();
        sessionCAGInvitationDto.setDateInvitationTime(date.format(formatter));
        SessionCAGInvitationDto saved = save(sessionCAGInvitationDto);
        if (saved != null) {
            return saved;
        }
        return null;
    }
}
