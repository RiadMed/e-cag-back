package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.MailGabaritDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.MailGabarit;
import dz.restmapping.apps.services.GenericService;

public interface MailGabaritService extends GenericService<MailGabarit, MailGabaritDto, Integer> {

    MailGabaritDto findMailGabaritByCode(String code);
}
