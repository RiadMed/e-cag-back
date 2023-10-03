package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionPlanification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SessionPlanificationDao extends JpaRepository<SessionPlanification, Long>, QuerydslPredicateExecutor<SessionPlanification>, JpaSpecificationExecutor<SessionPlanification> {
}
