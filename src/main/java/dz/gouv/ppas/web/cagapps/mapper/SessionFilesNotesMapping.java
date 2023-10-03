package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionFilesNotesDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionFilesNotes;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SessionFilesNotesMapping extends GenericMapper<SessionFilesNotes, SessionFilesNotesDto> {

    SessionFilesNotesMapping INSTANCE = Mappers.getMapper(SessionFilesNotesMapping.class);

    @Mappings({
            @Mapping(source = "id.sessionCAGFilesId", target = "sessionCAGFilesId"),
            @Mapping(source = "id.username", target = "username")
    })
    @Override
    SessionFilesNotesDto toDto(SessionFilesNotes sessionFilesNotes);

    @InheritInverseConfiguration
    @Override
    SessionFilesNotes toModel(SessionFilesNotesDto sessionFilesNotesDto);
}
