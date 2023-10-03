package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionPlanificationDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionPlanification;
import dz.gouv.ppas.web.cagapps.business.services.SessionPlanificationService;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.SESSION_PLANIF_ROOT)
public class SessionPlanificationController extends GenericRestController<SessionPlanificationService, SessionPlanification, SessionPlanificationDto, Long> {
}
