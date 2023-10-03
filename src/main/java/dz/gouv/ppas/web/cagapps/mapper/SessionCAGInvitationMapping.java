package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGInvitationDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGInvitation;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface SessionCAGInvitationMapping extends GenericMapper<SessionCAGInvitation, SessionCAGInvitationDto> {

    SessionCAGInvitationMapping INSTANCE = Mappers.getMapper(SessionCAGInvitationMapping.class);

    @Mappings({
            @Mapping(source = "sessionCAG.id", target = "sessionCAGId"),
            @Mapping(source = "sessionCAG.code", target = "sessionCAGCode"),
            @Mapping(source = "sessionCAG.adresse", target = "sessionCAGAdresse"),
            @Mapping(source = "sessionCAG.typeSession", target = "sessionCAGTypeSession"),
            @Mapping(source = "sessionCAG.organisation.label", target = "sessionCAGOrganisationLabel"),
            @Mapping(target = "invitationStatusDateTime", expression = "java(SessionCAGInvitationMapping.convertDateToString(sessionCAGInvitation.getInvitationStatusDate()))"),
            @Mapping(target = "dateInvitationTime", expression = "java(SessionCAGInvitationMapping.convertDateToString(sessionCAGInvitation.getDateInvitation()))")
    })
    @Override
    SessionCAGInvitationDto toDto(SessionCAGInvitation sessionCAGInvitation);


    @Mappings({
            @Mapping(target = "sessionCAG.id", source = "sessionCAGId"),
            @Mapping(target = "sessionCAG.code", source = "sessionCAGCode"),
            @Mapping(target = "sessionCAG.adresse", source = "sessionCAGAdresse"),
            @Mapping(target = "sessionCAG.typeSession", source = "sessionCAGTypeSession"),
            @Mapping(target = "sessionCAG.organisation.label", source = "sessionCAGOrganisationLabel")
})
@Override
    SessionCAGInvitation toModel(SessionCAGInvitationDto sessionCAGInvitationDto);


static String convertDateToString(LocalDateTime date){
        if(date!=null){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return date.format(formatter);
        }
        return"";
        }

static LocalDateTime convertStringToDateTime(String date){
        if(date!=null||!"".equals(date)){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(date,formatter);
        }
        return null;
        }
        }
