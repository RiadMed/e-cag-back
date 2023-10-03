package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGInvitationDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGInvitation;
import dz.restmapping.apps.services.GenericService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SessionCAGInvitationServices extends GenericService<SessionCAGInvitation, SessionCAGInvitationDto, Long> {

    EntityResponse<List<SessionCAGInvitationDto>> saveAndSendInvitations(List<SessionCAGInvitationDto> list);

    List<SessionCAGInvitationDto> findSessionCAGInvitationByMail(String mail);

    EntityResponse<SessionCAGInvitationDto> changeStatus(Long id);

    EntityResponse<SessionCAGInvitationDto> checkPresences(@RequestBody SessionCAGInvitationDto sessionCAGInvitationDto);

    List<SessionCAGInvitationDto> checkPresencesAll(SessionCAGDto sessionCAGDto);

    List<SessionCAGInvitationDto> checkAbsenceAll(SessionCAGDto sessionCAGDto);
}
