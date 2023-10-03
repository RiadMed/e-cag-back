package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.RolesDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.Roles;
import dz.gouv.ppas.web.cagapps.business.repositories.RolesDao;
import dz.gouv.ppas.web.cagapps.business.services.RolesServices;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolesServicesIMPL extends GenericServiceImpl<RolesDao, Roles, RolesDto, Integer> implements RolesServices {
}
