package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationTypeDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.OrganisationType;
import dz.gouv.ppas.web.cagapps.business.services.OrganisationTypeServices;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.ORGANISATION_TYPE_ROOT)
public class OrganisationTypeController extends GenericRestController<OrganisationTypeServices, OrganisationType, OrganisationTypeDto, Integer> {
}
