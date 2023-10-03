package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.AccessTokenDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.AccessToken;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccessTokenMapping extends GenericMapper<AccessToken, AccessTokenDto> {

    AccessTokenMapping INSTANCE = Mappers.getMapper(AccessTokenMapping.class);

    @Mappings({
            @Mapping(source = "user.id", target = "userId")
    })
    @Override
    AccessTokenDto toDto(AccessToken accessToken);

    @InheritInverseConfiguration
    @Override
    AccessToken toModel(AccessTokenDto accessTokenDto);
}
