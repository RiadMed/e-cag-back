package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAG;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", uses = {SessionStatusMapping.class, SessionCAGInvitationMapping.class})
public interface SessionCAGMapping extends GenericMapper<SessionCAG, SessionCAGDto> {

    SessionCAGMapping INSTANCE = Mappers.getMapper(SessionCAGMapping.class);

    @Mappings({
            @Mapping(source = "organisation.id", target = "organisationId"),
            @Mapping(source = "organisation.label", target = "organisationLabel"),
            @Mapping(source = "organisation.adresse", target = "organisationAdresse"),
            @Mapping(source = "status.id", target = "statusId"),
            @Mapping(source = "status.label", target = "statusLabel"),
            @Mapping(target = "sessionDateTime", expression = "java(SessionCAGMapping.convertDateToString(sessionCAG.getSessionDate()))"),
            @Mapping(source = "invitations", target = "invitationsList")
    })
    @Override
    SessionCAGDto toDto(SessionCAG sessionCAG);

    @Mappings({
            @Mapping(target = "organisation.id", source = "organisationId"),
            @Mapping(target = "organisation.label", source = "organisationLabel"),
            @Mapping(target = "organisation.adresse", source = "organisationAdresse"),
            @Mapping(target = "status.id", source = "statusId"),
            @Mapping(target = "status.label", source = "statusLabel"),
            @Mapping(target = "sessionDate", expression = "java(SessionCAGMapping.convertStringToDateTime(sessionCAGDto.getSessionDateTime()))"),
            @Mapping(target = "invitations", source = "invitationsList")
    })
    @Override
    SessionCAG toModel(SessionCAGDto sessionCAGDto);

    static String convertDateToString(LocalDateTime date) {
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return date.format(formatter);
        }
        return "";
    }

    static LocalDateTime convertStringToDateTime(String date) {
        if (date != null || !"".equals(date)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(date, formatter);
        }
        return null;
    }
}
