package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionCAGInvitationDao extends JpaRepository<SessionCAGInvitation, Long>, QuerydslPredicateExecutor<SessionCAGInvitation>, JpaSpecificationExecutor<SessionCAGInvitation> {

    List<SessionCAGInvitation> findSessionCAGInvitationByMailAndInvitationStatus(String mail, boolean status);

    @Query("select i from SessionCAGInvitation i where i.sessionCAG.organisation.id=:orgId and i.sessionCAG.status.label not in :status ")
    List<SessionCAGInvitation> findByOrgIdAndStatus(@Param("orgId") Integer orgId, @Param("status") List<String> status);
}
