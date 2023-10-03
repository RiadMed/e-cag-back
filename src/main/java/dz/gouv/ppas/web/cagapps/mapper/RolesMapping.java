package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.RolesDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.Roles;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RolesMapping extends GenericMapper<Roles, RolesDto> {

    RolesMapping INSTANCE = Mappers.getMapper(RolesMapping.class);

    @Mappings({
            @Mapping(source = "id", target = "key"),
            @Mapping(source = "menus", target = "menusList"),
    })
    @Override
    RolesDto toDto(Roles roles);

    @InheritInverseConfiguration
    @Override
    Roles toModel(RolesDto rolesDto);
}
