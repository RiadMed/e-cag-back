package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionResolutionsDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionResolutions;
import dz.gouv.ppas.web.cagapps.business.repositories.SessionResolutionsDao;
import dz.gouv.ppas.web.cagapps.business.services.SessionResolutionsService;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SessionResolutionsServiceIMPL extends GenericServiceImpl<SessionResolutionsDao, SessionResolutions, SessionResolutionsDto, Long> implements SessionResolutionsService {

    private final SessionResolutionsDao sessionResolutionsDao;

    @Override
    public List<SessionResolutionsDto> getSessionCagResultions(Long sessionId) {
        return mapToDto(sessionResolutionsDao.getSessionCagResultions(sessionId));
    }
}
