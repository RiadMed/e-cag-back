package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGFilesDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.FileRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.NextStatusRequest;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAG;
import dz.restmapping.apps.services.GenericService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SessionCAGServices extends GenericService<SessionCAG, SessionCAGDto, Long> {

    EntityResponse<SessionCAGDto>  saveSesseionCAG(SessionCAGDto entity);

    EntityResponse<SessionCAGDto> annulerLaSessionPlannifier(Long id);

    Optional<SessionCAGDto> findSessionCAGByCode(String code);

    EntityResponse<SessionCAGDto> addPVFile(FileRequest file) throws IOException;

    String getFiles(Long idFile);

    EntityResponse<SessionCAGDto> goToNextStatus(NextStatusRequest nextStatusRequest);

    EntityResponse<SessionCAGDto> demarrerLaReunion(SessionCAGDto sessionCAGDto);
}
