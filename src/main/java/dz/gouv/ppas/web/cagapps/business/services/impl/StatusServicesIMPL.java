package dz.gouv.ppas.web.cagapps.business.services.impl;

import com.googlecode.jmapper.JMapper;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.StatusDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.Status;
import dz.gouv.ppas.web.cagapps.business.repositories.StatusDao;
import dz.gouv.ppas.web.cagapps.business.services.StatusServices;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class StatusServicesIMPL extends GenericServiceImpl<StatusDao, Status, StatusDto, Integer> implements StatusServices {

    private final StatusDao statusDao;

    private final JMapper<StatusDto, Status> statusJMapper = new JMapper<>(StatusDto.class, Status.class);
    private final JMapper<Status, StatusDto> statusDtoJMapper = new JMapper<>(Status.class, StatusDto.class);

    @Override
    public StatusDto findStatusByLabel(String label) {
        return mapToDto(statusDao.findStatusByLabel(label));
    }

    @Override
    public Optional<StatusDto> findById(Integer id) {
        StatusDto status = new StatusDto();
        status.setLabel("En Cours");
        statusDao.exists(Example.of(statusDtoJMapper.getDestination(status)));
        Optional<Status> statusOpt = statusDao.findById(id);
        if (statusOpt.isPresent())
            return Optional.of(statusJMapper.getDestination(statusOpt.get()));
        return Optional.empty();
    }
}
