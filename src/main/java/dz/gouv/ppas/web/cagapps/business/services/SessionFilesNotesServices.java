package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionFileView;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionFilesNotesDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionFilesNotes;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionFilesNotesPK;
import dz.restmapping.apps.services.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

public interface SessionFilesNotesServices extends GenericService<SessionFilesNotes, SessionFilesNotesDto, SessionFilesNotesPK> {
    Page<SessionFileView> getFilteNote(Long sessionId,int page, String username);

    void deleteNote(Long fileId, String username);

    EntityResponse<SessionFilesNotesDto> saveNote(@RequestBody SessionFileView sessionFileView);
}
