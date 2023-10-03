package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrganisationDao extends JpaRepository<Organisation, Integer>, QuerydslPredicateExecutor<Organisation>, JpaSpecificationExecutor<Organisation> {
}
