package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDataTable;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAG;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {SessionCAGMapping.class})
public interface SessionCAGDAtaTableMapping extends GenericMapper<SessionCAG, SessionCAGDataTable> {

    SessionCAGDAtaTableMapping INSTANCE = Mappers.getMapper(SessionCAGDAtaTableMapping.class);

    @Mappings({
            @Mapping(source = "organisation.id", target = "organisationId"),
            @Mapping(source = "organisation.label", target = "organisationLabel"),
            @Mapping(source = "status.id", target = "statusId"),
            @Mapping(source = "status.label", target = "statusLabel"),
            @Mapping(target = "sessionDateTime", expression = "java(SessionCAGMapping.convertDateToString(sessionCAG.getSessionDate()))")
    })
    @Override
    SessionCAGDataTable toDto(SessionCAG sessionCAG);

    @Mappings({
            @Mapping(target = "organisation.id", source = "organisationId"),
            @Mapping(target = "organisation.label", source = "organisationLabel"),
            @Mapping(target = "status.id", source = "statusId"),
            @Mapping(target = "status.label", source = "statusLabel"),
            @Mapping(target = "sessionDate", expression = "java(SessionCAGMapping.convertStringToDateTime(sessionCAGDataTable.getSessionDateTime()))")
    })
    @Override
    SessionCAG toModel(SessionCAGDataTable sessionCAGDataTable);
}
