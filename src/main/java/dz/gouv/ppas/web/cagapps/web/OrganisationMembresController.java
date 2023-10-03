package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.MembresInviterDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationMembresDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.ResponsableOrganisationDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.OrganisationMembres;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.OrganisationMembresPK;
import dz.gouv.ppas.web.cagapps.business.services.OrganisationMembresServices;
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
@RequestMapping(StaticData.ApiMapping.ORGANISATION_MEMBERS_ROOT)
public class OrganisationMembresController extends GenericRestController<OrganisationMembresServices, OrganisationMembres, OrganisationMembresDto, OrganisationMembresPK> {

    private final OrganisationMembresServices organisationMembresServices;

    @GetMapping(
            params = {"usercode"}
    )
    List<ResponsableOrganisationDto> getOrganisationResponsable(@RequestParam("usercode") String usercode) {
        return organisationMembresServices.getOrganisationResponsable(usercode);
    }

    @GetMapping(
            params = {"orgId"}
    )
    public List<MembresInviterDto> getMembresInviterAuCAG(@RequestParam("orgId") Integer organisationId) {
        return organisationMembresServices.getMembresInviterAuCAG(organisationId);
    }

}
