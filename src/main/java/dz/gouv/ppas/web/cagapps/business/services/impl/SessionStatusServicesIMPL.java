package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionStatusDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionStatus;
import dz.gouv.ppas.web.cagapps.business.repositories.SessionStatusDao;
import dz.gouv.ppas.web.cagapps.business.services.SessionStatusServices;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SessionStatusServicesIMPL extends GenericServiceImpl<SessionStatusDao, SessionStatus, SessionStatusDto, Long> implements SessionStatusServices {

    private final SessionStatusDao sessionStatusDao;

    @Override
    public List<SessionStatusDto> findSessionStatusBySessionCAGId(Long sessionId) {
        return mapToDto(sessionStatusDao.findSessionStatusBySessionCAGId(sessionId));
    }
}
