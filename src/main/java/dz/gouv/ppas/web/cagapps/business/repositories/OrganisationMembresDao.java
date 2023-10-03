package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.MembresInviter;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.OrganisationMembres;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.OrganisationMembresPK;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.ResponsableOrganisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface OrganisationMembresDao extends JpaRepository<OrganisationMembres, OrganisationMembresPK>, QuerydslPredicateExecutor<OrganisationMembres>, JpaSpecificationExecutor<OrganisationMembres> {

    @Query("select new dz.gouv.ppas.web.cagapps.business.data.entities.apps.ResponsableOrganisation(om, o) from OrganisationMembres om, Organisation o where om.id.membreUserCode = ?1 and om.id.organisation.id = o.id")
    List<ResponsableOrganisation> getOrganisationResponsable(String usercode);

    @Query("select new dz.gouv.ppas.web.cagapps.business.data.entities.apps.MembresInviter(u) from OrganisationMembres om, User u where om.id.organisation.id = ?1 and  om.id.membreUserCode = u.accountName")
    List<MembresInviter> getMembresInviterAuCAG(Integer organisationId);

    @Query("select om from OrganisationMembres om where om.id.membreUserCode = ?1 and om.id.organisation.activer = true order by om.id.organisation.label ASC")
    List<OrganisationMembres> findByUserCode(String usercode);

    @Query("select om from OrganisationMembres om where om.id.membreUserCode = ?1 and om.id.organisation.id = ?2")
    OrganisationMembres findByIdOrgUsername(String username, Integer organisationId);


}
