package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionResolutionsDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.enums.StatusEnum;
import dz.gouv.ppas.web.cagapps.business.data.dto.statistic.InvitationStatistic;
import dz.gouv.ppas.web.cagapps.business.data.dto.statistic.ResolutionStatistic;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionResolutions;
import dz.gouv.ppas.web.cagapps.business.repositories.SessionResolutionsDao;
import dz.gouv.ppas.web.cagapps.business.services.SessionResolutionsService;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SessionResolutionsServiceIMPL extends GenericServiceImpl<SessionResolutionsDao, SessionResolutions, SessionResolutionsDto, Long> implements SessionResolutionsService {

    private final SessionResolutionsDao sessionResolutionsDao;

    @Override
    public List<SessionResolutionsDto> getSessionCagResultions(Long sessionId) {
        return mapToDto(sessionResolutionsDao.getSessionCagResultions(sessionId));
    }

    @Override
    public ResolutionStatistic checkResolutions(Integer organisationId) {
        List<SessionResolutionsDto> list = mapToDto(sessionResolutionsDao.getResolutionByOrgId(organisationId, Arrays.asList(StatusEnum.PLANIFIER.toString(), StatusEnum.ANNULER.toString(), StatusEnum.PV_VALIDER.toString())));
        int resolu = list.stream().filter(x -> x.isStatus()).collect(Collectors.toList()).size();
        return new ResolutionStatistic(resolu, list.size() - resolu);
    }
}
