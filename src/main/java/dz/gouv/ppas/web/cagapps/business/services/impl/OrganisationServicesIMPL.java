package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.Organisation;
import dz.gouv.ppas.web.cagapps.business.repositories.OrganisationDao;
import dz.gouv.ppas.web.cagapps.business.services.OrganisationServices;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganisationServicesIMPL extends GenericServiceImpl<OrganisationDao, Organisation, OrganisationDto, Integer> implements OrganisationServices {
}
