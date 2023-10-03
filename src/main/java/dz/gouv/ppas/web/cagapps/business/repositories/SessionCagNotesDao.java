package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCagNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SessionCagNotesDao extends JpaRepository<SessionCagNotes, Long>, QuerydslPredicateExecutor<SessionCagNotes>, JpaSpecificationExecutor<SessionCagNotes> {
}
