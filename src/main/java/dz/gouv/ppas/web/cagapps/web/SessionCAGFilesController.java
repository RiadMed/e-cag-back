package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGFilesDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGFiles;
import dz.gouv.ppas.web.cagapps.business.services.SessionCAGFilesServices;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.SESSION_CAG_FILES_ROOT)
public class SessionCAGFilesController extends GenericRestController<SessionCAGFilesServices, SessionCAGFiles, SessionCAGFilesDto, Long> {

    private final SessionCAGFilesServices sessionCAGFilesServices;

    @PostMapping(value = "/files")
    public EntityResponse<SessionCAGFilesDto> saveFile(@RequestBody SessionCAGFilesDto file) throws IOException {
        return sessionCAGFilesServices.saveFile(file);
    }

    @GetMapping(value = "/files", params = {"idFile"})
    public String getFiles(@RequestParam("idFile") Long idFile) {
        return sessionCAGFilesServices.getFiles(idFile);
    }

    @GetMapping(value = "/filesString", params = {"idFile"})
    public String getListOfFiles(@RequestParam("idFile") Long idFile) {
        return service.getListofFiles(idFile);
    }

    @GetMapping(value = "/terminer", params = {"idSession"})
    public EntityResponse<SessionCAGDto> terminerLeTelechargement(@RequestParam("idSession") Long sessionCAGId) {
        return sessionCAGFilesServices.terminerLeTelechargement(sessionCAGId);
    }


    @GetMapping(params = {"idSession"})
    public List<SessionCAGFilesDto> getListSessionFilesDtos(@RequestParam("idSession") Long sessionCAGId) {
        return sessionCAGFilesServices.getListSessionFile(sessionCAGId);
    }

    @GetMapping(value = "/pages", params = {"idSession"})
    Page<SessionCAGFilesDto> findSessionByPage(Long sessionId) {
        return sessionCAGFilesServices.findSessionByPage(sessionId);
    }

}
