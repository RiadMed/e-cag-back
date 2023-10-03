package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.UserDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.User;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {RolesMapping.class})
public interface UserMapping extends GenericMapper<User, UserDto> {

    UserMapping INSTANCE = Mappers.getMapper(UserMapping.class);

    @Mappings({
            @Mapping(source = "id", target = "key"),
            @Mapping(source = "roles", target = "rolesList"),
    })
    @Override
    UserDto toDto(User user);

    @InheritInverseConfiguration
    @Override
    User toModel(UserDto userDto);
}
