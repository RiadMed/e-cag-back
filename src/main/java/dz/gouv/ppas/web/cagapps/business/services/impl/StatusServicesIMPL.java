package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.StatusDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.Status;
import dz.gouv.ppas.web.cagapps.business.repositories.StatusDao;
import dz.gouv.ppas.web.cagapps.business.services.StatusServices;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class StatusServicesIMPL extends GenericServiceImpl<StatusDao, Status, StatusDto, Integer> implements StatusServices {

    private final StatusDao statusDao;

    @Override
    public StatusDto findStatusByLabel(String label) {
        return mapToDto(statusDao.findStatusByLabel(label));
    }
}
