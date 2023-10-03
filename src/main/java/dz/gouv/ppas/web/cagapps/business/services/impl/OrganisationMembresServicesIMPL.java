package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.MembresInviterDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationMembresDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.ResponsableOrganisationDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.enums.RoleEnum;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.Roles;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.MembresInviter;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.OrganisationMembres;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.OrganisationMembresPK;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.ResponsableOrganisation;
import dz.gouv.ppas.web.cagapps.business.repositories.OrganisationMembresDao;
import dz.gouv.ppas.web.cagapps.business.repositories.RolesDao;
import dz.gouv.ppas.web.cagapps.business.services.OrganisationMembresServices;
import dz.gouv.ppas.web.cagapps.business.services.RolesServices;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class OrganisationMembresServicesIMPL extends GenericServiceImpl<OrganisationMembresDao, OrganisationMembres, OrganisationMembresDto, OrganisationMembresPK> implements OrganisationMembresServices {

    private final RolesDao rolesDao;
    private final OrganisationMembresDao organisationMembresDao;

    @Override
    public List<ResponsableOrganisationDto> getOrganisationResponsable(String usercode) {
        List<ResponsableOrganisation> responsableOrganisationList = organisationMembresDao.getOrganisationResponsable(usercode);
        if (!responsableOrganisationList.isEmpty())
            return responsableOrganisationList.stream().map(x -> new ResponsableOrganisationDto(x)).collect(Collectors.toList());
        else
            return null;
    }

    @Override
    public List<MembresInviterDto> getMembresInviterAuCAG(Integer organisationId) {
        List<MembresInviter> list = organisationMembresDao.getMembresInviterAuCAG(organisationId);
        Roles roles = rolesDao.findRolesByLabel(RoleEnum.Membre.toString());
        list = list.stream().filter(x -> x.getRoles().contains(roles)).collect(Collectors.toList());
        return list.stream().map((data) -> {
            return new MembresInviterDto(data);
        }).collect(Collectors.toList());
    }

    @Override
    public List<OrganisationMembresDto> findByUserCode(String usercode) {
        return mapToDto(organisationMembresDao.findByUserCode(usercode));
    }

    @Override
    public OrganisationMembresDto findByIdOrgUsername(String username, Integer organisationId) {
        return mapToDto(organisationMembresDao.findByIdOrgUsername(username, organisationId));
    }


}
