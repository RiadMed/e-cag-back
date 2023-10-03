package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGConfig;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAG;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface SessionCAGConfigMapping extends GenericMapper<SessionCAG, SessionCAGConfig> {

    SessionCAGConfigMapping INSTANCE = Mappers.getMapper(SessionCAGConfigMapping.class);

    @Mappings({
            @Mapping(source = "organisation.id", target = "organisationId"),
            @Mapping(source = "organisation.label", target = "organisationLabel"),
            @Mapping(source = "status.id", target = "statusId"),
            @Mapping(source = "status.label", target = "statusLabel"),
            @Mapping(target = "sessionDateTime", expression = "java(SessionCAGMapping.convertDateToString(sessionCAG.getSessionDate()))")
    })
    @Override
    SessionCAGConfig toDto(SessionCAG sessionCAG);

    @Override
    @Mappings({
            @Mapping(target = "organisation.id", source = "organisationId"),
            @Mapping(target = "organisation.label", source = "organisationLabel"),
            @Mapping(target = "status.id", source = "statusId"),
            @Mapping(target = "status.label", source = "statusLabel")
    })
    SessionCAG toModel(SessionCAGConfig sessionCAGConfig);

    static String convertDateToString(LocalDate date) {
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm");
            return date.format(formatter);
        }
        return "";
    }

}
