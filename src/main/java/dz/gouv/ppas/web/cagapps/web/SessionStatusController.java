package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionStatusDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionStatus;
import dz.gouv.ppas.web.cagapps.business.services.SessionStatusServices;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.STATUS_SESSIONS_ROOT)
public class SessionStatusController extends GenericRestController<SessionStatusServices, SessionStatus, SessionStatusDto, Long> {

    private final SessionStatusServices sessionStatusServices;

    @GetMapping(params = {"sessionId"})
    public List<SessionStatusDto> findSessionStatusBySessionCAGId(@RequestParam("sessionId") Long sessionId) {
        return sessionStatusServices.findSessionStatusBySessionCAGId(sessionId);
    }
}
