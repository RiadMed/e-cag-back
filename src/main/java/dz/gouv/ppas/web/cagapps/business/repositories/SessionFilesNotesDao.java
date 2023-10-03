package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionFilesNotes;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionFilesNotesPK;
import dz.gouv.ppas.web.cagapps.business.data.entities.wrapper.SessionFileNoteWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface SessionFilesNotesDao extends JpaRepository<SessionFilesNotes, SessionFilesNotesPK>, QuerydslPredicateExecutor<SessionFilesNotes>, JpaSpecificationExecutor<SessionFilesNotes> {

//    @Query("select new dz.gouv.ppas.web.cagapps.business.data.entities.wrapper.SessionFileNoteWrapper(sf, sn.note) from SessionCAGFiles sf, SessionFilesNotes sn " +
//            "where sf.sessionCAG.id = ?1 and sn.id.username = ?2 and sf.id = sn.id.sessionCAGFiles.id")
//    List<SessionFileNoteWrapper> getFilteNote(Long sessionId, String username);

}
