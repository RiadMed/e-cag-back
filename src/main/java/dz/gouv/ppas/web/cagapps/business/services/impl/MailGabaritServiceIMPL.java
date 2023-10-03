package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.MailGabaritDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.MailGabarit;
import dz.gouv.ppas.web.cagapps.business.repositories.MailGabaritDao;
import dz.gouv.ppas.web.cagapps.business.services.MailGabaritService;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class MailGabaritServiceIMPL extends GenericServiceImpl<MailGabaritDao, MailGabarit, MailGabaritDto, Integer> implements MailGabaritService {

    private final MailGabaritDao mailGabaritDao;

    @Override
    public MailGabaritDto findMailGabaritByCode(String code) {
        return mapToDto(mailGabaritDao.findMailGabaritByCode(code));
    }
}
