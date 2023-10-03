package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionStatusDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionStatus;
import dz.restmapping.apps.services.GenericService;

import java.util.List;

public interface SessionStatusServices extends GenericService<SessionStatus, SessionStatusDto, Long> {

    List<SessionStatusDto> findSessionStatusBySessionCAGId(Long sessionId);
}
