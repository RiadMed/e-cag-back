package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGConfig;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAG;
import dz.restmapping.apps.services.GenericService;

public interface SessionCAGConfigService extends GenericService<SessionCAG, SessionCAGConfig, Long> {

    EntityResponse<SessionCAGConfig> validerDate(SessionCAGConfig sessionCAGConfig);
}
