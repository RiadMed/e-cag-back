package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDataTable;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGFilesDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.enums.StatusEnum;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.EntityResponse;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.FileRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.NextStatusRequest;
import dz.gouv.ppas.web.cagapps.business.data.dto.request.NotificationResponse;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAG;
import dz.gouv.ppas.web.cagapps.business.services.SessionCAGDataTableService;
import dz.gouv.ppas.web.cagapps.business.services.SessionCAGServices;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.tools.AppsUtils;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.SESSION_CAG_ROOT)
public class SessionCAGController extends GenericRestController<SessionCAGServices, SessionCAG, SessionCAGDto, Long> {

    private final SessionCAGServices sessionCAGServices;
    private final SessionCAGDataTableService sessionCAGDAtaTableService;

    @PostMapping("/saveDate")
    public EntityResponse<SessionCAGDto> saveDate(@RequestBody SessionCAGDto entity) {
        return sessionCAGServices.saveSesseionCAG(entity);
    }

    @GetMapping(
            params = {"orgId", "annnee"}
    )
    public List<SessionCAGDataTable> findLastSession(@RequestParam("orgId") List<Integer> organisationId, @RequestParam("annnee") Integer annnee) {
        return sessionCAGDAtaTableService.findLastSession(organisationId, annnee);
    }

    @GetMapping(
            params = {"orgId", "annnee", "mois"}
    )
    public List<SessionCAGDataTable> findCurrent(@RequestParam("orgId") List<Integer> organisationId, @RequestParam("annnee") Integer annnee, @RequestParam("mois") Integer mois) {
        return sessionCAGDAtaTableService.findCurrent(organisationId, annnee, mois);
    }


    @GetMapping(
            params = {"orgId"}
    )
    public NotificationResponse<SessionCAGDataTable> getPlannedSession(@RequestParam("orgId") Integer organisationId) {
        return new NotificationResponse<>(sessionCAGDAtaTableService.getPlannedSession(organisationId, StatusEnum.PLANIFIER.toString()));
    }

    @GetMapping(
            value = "/countSession",
            params = {"orgId"}
    )
    public NotificationResponse<SessionCAGDataTable> getCountPlannedSession(@RequestParam("orgId") List<Integer> organisationId) {
        return new NotificationResponse<>(sessionCAGDAtaTableService.getCountPlannedSession(organisationId));
    }

    @DeleteMapping(
            value = "/annuler",
            params = {"id"})
    public EntityResponse<SessionCAGDto> annuleDate(@RequestParam("id") Long id) {
        return sessionCAGServices.annulerLaSessionPlannifier(id);
    }


    @GetMapping(
            value = "/dataGrid",
            params = {"page", "size", "sort", "field"}
    )
    public Page<SessionCAGDataTable> findAllDataForTable(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sort") String sort, @RequestParam("field") String field) {
        return this.sessionCAGDAtaTableService.findAll(PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), new String[]{field})));
    }

    @GetMapping(
            value = "/dataGrid",
            params = {"page", "size", "sort", "field", "search"}
    )
    public Page<SessionCAGDataTable> findAllDataForTableWithSearchParam(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sort") String sort, @RequestParam("field") String field, @RequestParam("search") String search) {
        return this.sessionCAGDAtaTableService.findAll(search, PageRequest.of(page, size, Sort.by(AppsUtils.getSortDirection(sort), new String[]{field})));
    }

    @PostMapping(value = "/addPVFile")
    public EntityResponse<SessionCAGDto> addPVFile(@RequestBody FileRequest file) throws IOException {
        return sessionCAGServices.addPVFile(file);
    }

    @GetMapping(value = "/files", params = {"idFile"})
    public String getFiles(@RequestParam("idFile") Long idFile) {
        return sessionCAGServices.getFiles(idFile);
    }

    @PostMapping(value = "/nextStep")
    public EntityResponse<SessionCAGDto> goToNextStatus(@RequestBody NextStatusRequest nextStatusRequest) {
        return sessionCAGServices.goToNextStatus(nextStatusRequest);
    }

    @PostMapping(value = "/demarrerLaReunion")
    public EntityResponse<SessionCAGDto> demarrerLaReunion(@RequestBody SessionCAGDto sessionCAGDto) {
        return sessionCAGServices.demarrerLaReunion(sessionCAGDto);
    }

}
