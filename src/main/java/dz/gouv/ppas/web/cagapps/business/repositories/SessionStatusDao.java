package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface SessionStatusDao extends JpaRepository<SessionStatus, Long>, QuerydslPredicateExecutor<SessionStatus>, JpaSpecificationExecutor<SessionStatus> {

    List<SessionStatus> findSessionStatusBySessionCAGId(Long sessionId);
}
