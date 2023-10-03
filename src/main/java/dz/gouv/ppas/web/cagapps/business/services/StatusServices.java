package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.StatusDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.Status;
import dz.restmapping.apps.services.GenericService;

public interface StatusServices extends GenericService<Status, StatusDto, Integer> {

    StatusDto findStatusByLabel(String label);
}
