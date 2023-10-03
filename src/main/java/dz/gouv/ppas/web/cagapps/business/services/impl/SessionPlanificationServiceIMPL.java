package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionPlanificationDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionPlanification;
import dz.gouv.ppas.web.cagapps.business.repositories.SessionPlanificationDao;
import dz.gouv.ppas.web.cagapps.business.services.SessionPlanificationService;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionPlanificationServiceIMPL extends GenericServiceImpl<SessionPlanificationDao, SessionPlanification, SessionPlanificationDto, Long> implements SessionPlanificationService {
}
