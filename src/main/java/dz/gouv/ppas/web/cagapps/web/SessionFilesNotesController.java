package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionFileView;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionFilesNotesDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionFilesNotes;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionFilesNotesPK;
import dz.gouv.ppas.web.cagapps.business.services.SessionFilesNotesServices;
import dz.gouv.ppas.web.cagapps.tools.AppsUtils;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.SESSION_CAG_FILES_NOTES_ROOT)
public class SessionFilesNotesController extends GenericRestController<SessionFilesNotesServices, SessionFilesNotes, SessionFilesNotesDto, SessionFilesNotesPK> {

    private final SessionFilesNotesServices sessionFilesNotesServices;

    @GetMapping(params = {"sessionId", "page"})
    public Page<SessionFileView> getFilteNote(@RequestParam("sessionId") Long sessionId, @RequestParam("page") int page) {
        return sessionFilesNotesServices.getFilteNote(sessionId, page, AppsUtils.getUserPrincipal().getUsername());
    }

    @DeleteMapping(params = {"fileId"})
    public void deleteNote(@RequestParam("fileId") Long fileId) {
        sessionFilesNotesServices.deleteNote(fileId, AppsUtils.getUserPrincipal().getUsername());
    }

    @PostMapping("/saveNote")
    public EntityResponse<SessionFilesNotesDto> saveNote(@RequestBody SessionFileView sessionFileView) {
        return sessionFilesNotesServices.saveNote(sessionFileView);
    }

}
