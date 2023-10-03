package dz.gouv.ppas.web.cagapps.business.repositories;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.MailGabarit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MailGabaritDao extends JpaRepository<MailGabarit, Integer>, QuerydslPredicateExecutor<MailGabarit>, JpaSpecificationExecutor<MailGabarit> {

    MailGabarit findMailGabaritByCode(String code);
}
