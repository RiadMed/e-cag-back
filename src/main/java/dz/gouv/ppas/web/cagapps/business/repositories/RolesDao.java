package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RolesDao extends JpaRepository<Roles, Integer>, QuerydslPredicateExecutor<Roles>, JpaSpecificationExecutor<Roles> {

    Roles findRolesByLabel(String label);
}
