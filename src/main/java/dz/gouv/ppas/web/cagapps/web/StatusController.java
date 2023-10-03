package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.StatusDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.Status;
import dz.gouv.ppas.web.cagapps.business.services.StatusServices;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.STATUS_ROOT)
public class StatusController extends GenericRestController<StatusServices, Status, StatusDto, Integer> {
}
