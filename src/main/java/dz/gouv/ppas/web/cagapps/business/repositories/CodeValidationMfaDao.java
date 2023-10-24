package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.CodeValidationMfa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface CodeValidationMfaDao extends JpaRepository<CodeValidationMfa, Long>, QuerydslPredicateExecutor<CodeValidationMfa>, JpaSpecificationExecutor<CodeValidationMfa> {

    @Query(value = "SELECT A " +
            "FROM CodeValidationMfa A " +
            "WHERE A.usercode = :usercode " +
            "AND A.numeroSeq = " +
            "(SELECT MAX(B.numeroSeq) " +
            "FROM CodeValidationMfa B " +
            "WHERE B.usercode = :usercode)")
    CodeValidationMfa getLastCodeValidationByUserCode(@Param("usercode") String usercode);

    @Modifying
    @Query("DELETE FROM CodeValidationMfa c where c.usercode = ?1")
    void deleteCodeValidationByUserCode(String userCode);

}
