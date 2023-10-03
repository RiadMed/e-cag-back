package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGFiles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionCAGFilesDao extends JpaRepository<SessionCAGFiles, Long>, QuerydslPredicateExecutor<SessionCAGFiles>, JpaSpecificationExecutor<SessionCAGFiles> {

    List<SessionCAGFiles> findSessionCAGFilesBySessionCAGId(Long sessionId);

    @Query(value = "select s from SessionCAGFiles s where s.sessionCAG.id = :sessionId")
    Page<SessionCAGFiles> findSessionByPage(@Param("sessionId") Long sessionId, Pageable pageable);

}
