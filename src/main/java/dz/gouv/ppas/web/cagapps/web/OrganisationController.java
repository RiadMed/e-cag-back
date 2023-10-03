package dz.gouv.ppas.web.cagapps.web;


import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationDetailDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.Organisation;
import dz.gouv.ppas.web.cagapps.business.services.OrganisationServices;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.ORGANISATION_ROOT)
public class OrganisationController extends GenericRestController<OrganisationServices, Organisation, OrganisationDto, Integer> {

    private final OrganisationServices organisationServices;

    @GetMapping("/details")
    public List<OrganisationDetailDto> getAllOrgDetail() {
        return organisationServices.findAll().stream().map(x -> new OrganisationDetailDto(x.getId(), x.getKey(), x.getLabel())).collect(Collectors.toList());
    }
}
