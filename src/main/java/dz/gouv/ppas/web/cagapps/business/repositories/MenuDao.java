package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MenuDao extends JpaRepository<Menu, Integer>, QuerydslPredicateExecutor<Menu>, JpaSpecificationExecutor<Menu> {
}
