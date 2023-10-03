package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionFileView;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionFilesNotesDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGFiles;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionFilesNotes;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionFilesNotesPK;
import dz.gouv.ppas.web.cagapps.business.repositories.SessionCAGFilesDao;
import dz.gouv.ppas.web.cagapps.business.repositories.SessionFilesNotesDao;
import dz.gouv.ppas.web.cagapps.business.services.SessionFilesNotesServices;
import dz.gouv.ppas.web.cagapps.tools.AppsUtils;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class SessionFilesNotesServicesIMPL extends GenericServiceImpl<SessionFilesNotesDao, SessionFilesNotes, SessionFilesNotesDto, SessionFilesNotesPK> implements SessionFilesNotesServices {

    private final SessionCAGFilesDao sessionCAGFilesDao;

    @Override
    public Page<SessionFileView> getFilteNote(Long sessionId, int page, String username) {
        Page<SessionCAGFiles> listFiles = sessionCAGFilesDao.findSessionByPage(sessionId, PageRequest.of(page, 5, Sort.by("id").descending()));
        return listFiles.map(x -> new SessionFileView(x, getNoteByUsername(x.getSessionFilesNotes(), username)));
    }

    @Override
    public void deleteNote(Long fileId, String username) {
        deleteById(new SessionFilesNotesPK(fileId, username));
    }

    @Override
    public EntityResponse<SessionFilesNotesDto> saveNote(SessionFileView sessionFileView) {
        SessionFilesNotesDto saved = save(SessionFilesNotesDto.builder()
                .sessionCAGFilesId(sessionFileView.getId())
                .username(AppsUtils.getUserPrincipal().getUsername())
                .note(sessionFileView.getNote())
                .build());
        if (saved != null) {
            return new EntityResponse<>(saved, "Note Ajouter!.");
        }
        return new EntityResponse<>(false, "Erreur dans l'ajout du note!. (8001)");
    }

    private String getNoteByUsername(List<SessionFilesNotes> listNotes, String username) {
        return listNotes.stream().filter(x -> username.equals(x.getId().getUsername())).map(SessionFilesNotes::getNote).findAny()
                .orElse(null);
    }
}
