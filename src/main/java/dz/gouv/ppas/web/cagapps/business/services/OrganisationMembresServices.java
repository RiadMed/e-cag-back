package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.MembresInviterDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationMembresDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.ResponsableOrganisationDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.OrganisationMembres;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.OrganisationMembresPK;
import dz.restmapping.apps.services.GenericService;

import java.util.List;

public interface OrganisationMembresServices extends GenericService<OrganisationMembres, OrganisationMembresDto, OrganisationMembresPK> {

    List<ResponsableOrganisationDto> getOrganisationResponsable(String usercode);

    List<MembresInviterDto> getMembresInviterAuCAG(Integer organisationId);

    List<OrganisationMembresDto> findByUserCode(String usercode);

    OrganisationMembresDto findByIdOrgUsername(String username, Integer organisationId);

}
