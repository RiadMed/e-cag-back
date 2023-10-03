package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.OrganisationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrganisationTypeDao extends JpaRepository<OrganisationType, Integer>, QuerydslPredicateExecutor<OrganisationType>, JpaSpecificationExecutor<OrganisationType> {
}
