package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCagNotesDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCagNotes;
import dz.gouv.ppas.web.cagapps.business.repositories.SessionCagNotesDao;
import dz.gouv.ppas.web.cagapps.business.services.SessionCagNotesServices;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionCagNotesServicesIMPL extends GenericServiceImpl<SessionCagNotesDao, SessionCagNotes, SessionCagNotesDto, Long> implements SessionCagNotesServices {
}
