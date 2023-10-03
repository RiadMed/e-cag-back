package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGFilesDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGFiles;
import dz.restmapping.apps.services.GenericService;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SessionCAGFilesServices extends GenericService<SessionCAGFiles, SessionCAGFilesDto, Long> {

    EntityResponse<SessionCAGFilesDto> saveFile(SessionCAGFilesDto file) throws IOException;

    EntityResponse<SessionCAGDto> terminerLeTelechargement(Long sessionCAGId);

    List<SessionCAGFilesDto> getListSessionFile(Long sessionCAGId);

    Page<SessionCAGFilesDto> findSessionByPage(Long sessionId);

    String getFiles(Long idFile);

    String getListofFiles(Long idFile);
}
