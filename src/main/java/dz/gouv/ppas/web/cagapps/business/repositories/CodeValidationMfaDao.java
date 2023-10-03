package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.CodeValidationMfa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CodeValidationMfaDao extends JpaRepository<CodeValidationMfa, Long>, QuerydslPredicateExecutor<CodeValidationMfa>, JpaSpecificationExecutor<CodeValidationMfa> {
}
