package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;
import java.util.UUID;

public interface AccessTokenDao extends JpaRepository<AccessToken, UUID>, QuerydslPredicateExecutor<AccessToken>, JpaSpecificationExecutor<AccessToken> {

    @Query("select a FROM AccessToken a where a.user.id = ?1")
    Optional<AccessToken> findByUser(Long userId);

    @Modifying
    @Query("DELETE FROM AccessToken a where a.user.id = ?1")
    void deleteTokenByUser(Long userId);
}
