package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface SessionCAGDao extends JpaRepository<SessionCAG, Long>, QuerydslPredicateExecutor<SessionCAG>, JpaSpecificationExecutor<SessionCAG> {

    @Query("select s from SessionCAG s where s.organisation.id IN ?1 and s.annee = ?2 order by s.sessionDate ASC")
    List<SessionCAG> findLastSession(List<Integer> organisationId, Integer annnee);

    @Query("select s from SessionCAG s where s.organisation.id IN ?1 and s.annee = ?2 and s.mois = ?3 order by s.sessionDate ASC")
    List<SessionCAG> findCurrent(List<Integer> organisationId, Integer annnee, Integer mois);

    @Query("select s from SessionCAG s where s.organisation.id = ?1 and s.status.label = ?2")
    List<SessionCAG> getPlannedSession(Integer orgId, String statusLabel);

    @Query("select count(s) from SessionCAG s where s.organisation.id IN ?1 and s.status.label = ?2")
    Integer getCountPlannedSession(List<Integer> orgId, String statusLabel);

    @Query("select s from SessionCAG s where s.code = ?1 and s.status.label NOT IN ?2")
    Optional<SessionCAG> findSessionCAGByCode(String code, List<String> statuts);
}
