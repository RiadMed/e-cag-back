package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDataTable;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAG;
import dz.restmapping.apps.services.GenericService;

import java.util.List;
import java.util.Optional;

public interface SessionCAGDataTableService extends GenericService<SessionCAG, SessionCAGDataTable, Long> {

    List<SessionCAGDataTable> findLastSession(List<Integer> organisationId, Integer annnee);

    List<SessionCAGDataTable> findCurrent(List<Integer> organisationId, Integer annnee, Integer mois);

    List<SessionCAGDataTable> getPlannedSession(Integer orgId, String statusLabel);

    Integer getCountPlannedSession(List<Integer> orgId);

    Optional<SessionCAGDataTable> findSessionCAGByCode(String code);
}
