package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface RoleMenuDao extends JpaRepository<RoleMenu, Integer>, QuerydslPredicateExecutor<RoleMenu>, JpaSpecificationExecutor<RoleMenu> {

    @Query("select rm  from RoleMenu rm where rm.role.id IN ?1 order by rm.menu.ordre ASC")
    List<RoleMenu> getMenuByRole(List<Integer> roleIds);
}
