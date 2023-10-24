package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionResolutionsDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.statistic.InvitationStatistic;
import dz.gouv.ppas.web.cagapps.business.data.dto.statistic.ResolutionStatistic;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionResolutions;
import dz.gouv.ppas.web.cagapps.business.services.SessionResolutionsService;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.RESOLUTIONS_ROOT)
public class SessionResolutionsController extends GenericRestController<SessionResolutionsService, SessionResolutions, SessionResolutionsDto, Long> {

    private final SessionResolutionsService sessionResolutionsService;

    @GetMapping(params = {"sessionId"})
    public List<SessionResolutionsDto> getSessionCagResultions(@RequestParam("sessionId") Long sessionId){
        return sessionResolutionsService.getSessionCagResultions(sessionId);
    }

    @Override
    @PostMapping
    public SessionResolutionsDto create(@RequestBody SessionResolutionsDto entity) {
        entity.setDateCreation(LocalDateTime.now());
        return super.create(entity);
    }

    @GetMapping(
            params = {"orgId"}
    )
    public ResolutionStatistic checkResolutions(@RequestParam("orgId") Integer organisationId) {
        return sessionResolutionsService.checkResolutions(organisationId);
    }

}
