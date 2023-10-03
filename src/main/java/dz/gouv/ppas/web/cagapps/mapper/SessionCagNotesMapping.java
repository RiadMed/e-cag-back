package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCagNotesDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCagNotes;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SessionCagNotesMapping extends GenericMapper<SessionCagNotes, SessionCagNotesDto> {
    SessionCagNotesMapping INSTANCE = Mappers.getMapper(SessionCagNotesMapping.class);

    @Override
    SessionCagNotesDto toDto(SessionCagNotes sessionCagNotes);

    @Override
    SessionCagNotes toModel(SessionCagNotesDto sessionCagNotesDto);
}
