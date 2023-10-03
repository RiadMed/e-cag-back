package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCagNotesDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCagNotes;
import dz.gouv.ppas.web.cagapps.business.services.SessionCagNotesServices;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.SESSION_CAG_NOTES_ROOT)
public class SessionCagNotesController extends GenericRestController<SessionCagNotesServices, SessionCagNotes, SessionCagNotesDto, Long> {
}
