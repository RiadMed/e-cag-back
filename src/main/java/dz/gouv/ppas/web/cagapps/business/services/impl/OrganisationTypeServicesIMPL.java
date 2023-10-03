package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationTypeDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.OrganisationType;
import dz.gouv.ppas.web.cagapps.business.repositories.OrganisationTypeDao;
import dz.gouv.ppas.web.cagapps.business.services.OrganisationTypeServices;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganisationTypeServicesIMPL extends GenericServiceImpl<OrganisationTypeDao, OrganisationType, OrganisationTypeDto, Integer> implements OrganisationTypeServices {
}
