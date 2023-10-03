package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionStatusDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionStatus;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface SessionStatusMapping extends GenericMapper<SessionStatus, SessionStatusDto> {

    SessionStatusMapping INSTANCE = Mappers.getMapper(SessionStatusMapping.class);

    @Mappings({
            @Mapping(source = "sessionCAG.id", target = "sessionId"),
            @Mapping(source = "status.id", target = "statusId"),
            @Mapping(source = "status.label", target = "statusLabel"),
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "user.firstName", target = "userFirstName"),
            @Mapping(source = "user.lastName", target = "userLastName"),
            @Mapping(target = "statusDateTime", expression = "java(SessionStatusMapping.convertDateToString(sessionStatus.getDate()))"),
    })
    @Override
    SessionStatusDto toDto(SessionStatus sessionStatus);

    @Mappings({
            @Mapping(target = "sessionCAG.id", source = "sessionId"),
            @Mapping(target = "status.id", source = "statusId"),
            @Mapping(target = "status.label", source = "statusLabel"),
            @Mapping(target = "user.id", source = "userId"),
            @Mapping(target = "user.firstName", source = "userFirstName"),
            @Mapping(target = "user.lastName", source = "userLastName")
    })
    @Override
    SessionStatus toModel(SessionStatusDto sessionStatusDto);

    static String convertDateToString(LocalDateTime date) {
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM");
            return date.format(formatter);
        }
        return "";
    }

    static LocalDateTime convertStringToDateTime(String date) {
        if (date != null || !date.equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM");
            return LocalDateTime.parse(date, formatter);
        }
        return null;
    }
}
