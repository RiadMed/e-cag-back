package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionResolutionsDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionResolutions;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SessionResolutionsMapping extends GenericMapper<SessionResolutions, SessionResolutionsDto> {

    SessionResolutionsMapping INSTANCE = Mappers.getMapper(SessionResolutionsMapping.class);

    @Mappings({
            @Mapping(source = "sessionCAG.id", target = "sessionCAGId"),
            @Mapping(source = "sessionCAG.code", target = "sessionCAGCode")
    })
    @Override
    SessionResolutionsDto toDto(SessionResolutions sessionResolutions);

    @InheritInverseConfiguration
    @Override
    SessionResolutions toModel(SessionResolutionsDto sessionResolutionsDto);
}
