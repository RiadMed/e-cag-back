package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface SessionCAGInvitationDao extends JpaRepository<SessionCAGInvitation, Long>, QuerydslPredicateExecutor<SessionCAGInvitation>, JpaSpecificationExecutor<SessionCAGInvitation> {

    List<SessionCAGInvitation> findSessionCAGInvitationByMailAndInvitationStatus(String mail, boolean status);
}
