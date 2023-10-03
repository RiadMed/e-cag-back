package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGConfig;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAG;
import dz.gouv.ppas.web.cagapps.business.services.SessionCAGConfigService;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.SESSION_CAG_CONFIG_ROOT)
public class SessionCAGConfigController extends GenericRestController<SessionCAGConfigService, SessionCAG, SessionCAGConfig, Long> {

    private final SessionCAGConfigService sessionCAGConfigService;

    @PostMapping("/validerDate")
    public EntityResponse<SessionCAGConfig> validerDate(@RequestBody SessionCAGConfig entity) {
        return sessionCAGConfigService.validerDate(entity);
    }
}
