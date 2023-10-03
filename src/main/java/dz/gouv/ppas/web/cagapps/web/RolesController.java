package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.RolesDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.Roles;
import dz.gouv.ppas.web.cagapps.business.services.RolesServices;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.ROLE_ROOT)
public class RolesController extends GenericRestController<RolesServices, Roles, RolesDto, Integer> {
}
