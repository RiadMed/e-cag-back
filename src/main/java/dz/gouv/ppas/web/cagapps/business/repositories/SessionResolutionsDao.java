package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionResolutions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionResolutionsDao extends JpaRepository<SessionResolutions, Long>, QuerydslPredicateExecutor<SessionResolutions>, JpaSpecificationExecutor<SessionResolutions> {

    @Query("select res from SessionResolutions res where res.sessionCAG.id = :sessionId")
    List<SessionResolutions> getSessionCagResultions(@Param("sessionId") Long sessionId);

    @Query("select res from SessionResolutions res where res.sessionCAG.organisation.id = :orgId and res.sessionCAG.status.label not in :status")
    List<SessionResolutions> getResolutionByOrgId(@Param("orgId") Integer orgId, @Param("status") List<String> status);

}
