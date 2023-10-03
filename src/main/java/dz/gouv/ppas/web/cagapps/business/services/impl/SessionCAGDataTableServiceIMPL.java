package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.UserDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDataTable;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionStatusDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.StatusDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.enums.StatusEnum;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAG;
import dz.gouv.ppas.web.cagapps.business.repositories.SessionCAGDao;
import dz.gouv.ppas.web.cagapps.business.services.SessionCAGDataTableService;
import dz.gouv.ppas.web.cagapps.tools.AppsUtils;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
@Transactional
public class SessionCAGDataTableServiceIMPL extends GenericServiceImpl<SessionCAGDao, SessionCAG, SessionCAGDataTable, Long> implements SessionCAGDataTableService {

    private final SessionCAGDao sessionCAGDao;

    @Override
    public List<SessionCAGDataTable> findLastSession(List<Integer> organisationId, Integer annnee) {
        return mapToDto(sessionCAGDao.findLastSession(organisationId, annnee));
    }

    @Override
    public List<SessionCAGDataTable> findCurrent(List<Integer> organisationId, Integer annnee, Integer mois) {
        return mapToDto(sessionCAGDao.findCurrent(organisationId, annnee, mois));
    }

    @Override
    public List<SessionCAGDataTable> getPlannedSession(Integer orgId, String statusLabel) {
        return mapToDto(sessionCAGDao.getPlannedSession(orgId, statusLabel));
    }

    @Override
    public Integer getCountPlannedSession(List<Integer> orgId) {
        return sessionCAGDao.getCountPlannedSession(orgId, StatusEnum.PLANIFIER.toString());
    }

    @Override
    public Optional<SessionCAGDataTable> findSessionCAGByCode(String code) {
        return sessionCAGDao.findSessionCAGByCode(code, Arrays.asList(StatusEnum.DATE_ANNULER.toString(), StatusEnum.ANNULER.toString())).map(x -> mapToDto(x));
    }
}
