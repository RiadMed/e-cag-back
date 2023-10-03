package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface StatusDao extends JpaRepository<Status, Integer>, QuerydslPredicateExecutor<Status>, JpaSpecificationExecutor<Status> {

    Status findStatusByLabel(String label);
}
