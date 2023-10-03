package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.UserDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGConfig;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionStatusDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.StatusDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.enums.StatusEnum;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAG;
import dz.gouv.ppas.web.cagapps.business.repositories.SessionCAGDao;
import dz.gouv.ppas.web.cagapps.business.services.*;
import dz.gouv.ppas.web.cagapps.tools.AppsUtils;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
@Transactional
public class SessionCAGConfigServiceIMPL extends GenericServiceImpl<SessionCAGDao, SessionCAG, SessionCAGConfig, Long> implements SessionCAGConfigService {

    private final UserService userService;
    private final StatusServices statusServices;
    private final SessionCAGServices sessionCAGServices;
    private final SessionStatusServices sessionStatusServices;

    @Override
    public EntityResponse<SessionCAGConfig> validerDate(SessionCAGConfig sessionCAGConfig) {
        Optional<SessionCAGDto> sessionCAGOpt = sessionCAGServices.findById(sessionCAGConfig.getId());
        if (!sessionCAGOpt.isPresent()) {
            return new EntityResponse<>("Session CAG N: " + sessionCAGConfig.getCode() + " non trouvée!. (6003)");
        }
        Optional<UserDto> user = userService.findByAccountName(AppsUtils.getUserPrincipal().getAccountName());
        if (!user.isPresent()) {
            return new EntityResponse<>("Utilsateur non trouvé. (6001)");
        }

        SessionCAGDto sessionCAG = sessionCAGOpt.get();
        StatusDto status = statusServices.findStatusByLabel(StatusEnum.EN_COURS.toString());
        sessionCAG.setStatusId(status.getId());
        sessionCAG.setStatusLabel(status.getLabel());
        sessionStatusServices.save(new SessionStatusDto(status.getId(), sessionCAG.getId(), user.get().getId()));
        return new EntityResponse<>(new SessionCAGConfig(sessionCAGServices.save(sessionCAG)));
    }
}
