package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionResolutionsDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.statistic.InvitationStatistic;
import dz.gouv.ppas.web.cagapps.business.data.dto.statistic.ResolutionStatistic;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionResolutions;
import dz.restmapping.apps.services.GenericService;

import java.util.List;

public interface SessionResolutionsService extends GenericService<SessionResolutions, SessionResolutionsDto, Long> {
    List<SessionResolutionsDto> getSessionCagResultions(Long sessionId);

    ResolutionStatistic checkResolutions(Integer organisationId);
}
