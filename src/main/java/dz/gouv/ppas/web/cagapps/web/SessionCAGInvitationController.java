package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGInvitationDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.NotificationResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGInvitation;
import dz.gouv.ppas.web.cagapps.business.services.SessionCAGInvitationServices;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.SESSION_CAG_INVITATION_ROOT)
public class SessionCAGInvitationController extends GenericRestController<SessionCAGInvitationServices, SessionCAGInvitation, SessionCAGInvitationDto, Long> {

    private final SessionCAGInvitationServices sessionCAGInvitationServices;

    @PostMapping(value = {"/invitations"}, produces = {"application/json"})
    public EntityResponse<List<SessionCAGInvitationDto>> sendInvitations(@RequestBody List<SessionCAGInvitationDto> list) {
        return sessionCAGInvitationServices.saveAndSendInvitations(list);
    }

    @GetMapping(
            value = "/notfications",
            params = {"mail"})
    public List<SessionCAGInvitationDto> findSessionCAGInvitationByMail(String mail) {
        return sessionCAGInvitationServices.findSessionCAGInvitationByMail(mail);
    }

    @GetMapping(
            value = "/countNotfications",
            params = {"mail"})
    public NotificationResponse<SessionCAGInvitationDto> contSessionCAGInvitationByMail(String mail) {
        return new NotificationResponse<>(new Integer(sessionCAGInvitationServices.findSessionCAGInvitationByMail(mail).size()));
    }

    @GetMapping(
            value = "/changeStatus",
            params = {"id"})
    public EntityResponse<SessionCAGInvitationDto> changeInvitationStatus(@RequestParam("id") Long id) {
        return sessionCAGInvitationServices.changeStatus(id);
    }

    @PostMapping(value = "/checkPresence")
    public EntityResponse<SessionCAGInvitationDto> checkPresences(@RequestBody SessionCAGInvitationDto sessionCAGInvitationDto) {
        return sessionCAGInvitationServices.checkPresences(sessionCAGInvitationDto);
    }


    @PostMapping(value = "/checkPresenceAll")
    public List<SessionCAGInvitationDto> checkPresencesAll(@RequestBody SessionCAGDto sessionCAGDto) {
        return sessionCAGInvitationServices.checkPresencesAll(sessionCAGDto);
    }

    @PostMapping(value = "/checkAbsenceAll")
    public List<SessionCAGInvitationDto> checkAbsenceAll(@RequestBody SessionCAGDto sessionCAGDto) {
        return sessionCAGInvitationServices.checkAbsenceAll(sessionCAGDto);
    }


}
