package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionCAGFilesDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGFiles;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.util.Base64Utils;

@Mapper(componentModel = "spring")
public interface SessionCAGFilesMapping extends GenericMapper<SessionCAGFiles, SessionCAGFilesDto> {

    SessionCAGFilesMapping INSTANCE = Mappers.getMapper(SessionCAGFilesMapping.class);

    @Mappings({
            @Mapping(source = "sessionCAG.id", target = "sessionCAGId"),
            @Mapping(source = "sessionCAG.code", target = "sessionCAGCode")
    })
    @Override
    SessionCAGFilesDto toDto(SessionCAGFiles sessionCAGFiles);

    @Mappings({
            @Mapping(target = "sessionCAG.id", source = "sessionCAGId"),
            @Mapping(target = "sessionCAG.code", source = "sessionCAGCode")
    })
    @Override
    SessionCAGFiles toModel(SessionCAGFilesDto sessionCAGFilesDto);
}
