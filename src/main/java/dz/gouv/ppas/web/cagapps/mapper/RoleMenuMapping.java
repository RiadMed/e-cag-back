package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.RoleMenu;
import dz.gouv.ppas.web.cagapps.business.data.dto.admin.RoleMenuDto;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMenuMapping extends GenericMapper<RoleMenu, RoleMenuDto> {

    RoleMenuMapping INSTANCE = Mappers.getMapper(RoleMenuMapping.class);

    @Mappings({
            @Mapping(source = "id", target = "key"),
            @Mapping(source = "role.id", target = "roleId"),
            @Mapping(source = "role.label", target = "roleLabel"),
            @Mapping(source = "menu.id", target = "menuId"),
            @Mapping(source = "menu.label", target = "menuLabel"),
            @Mapping(source = "menu.description", target = "menuDescription"),
            @Mapping(source = "menu.to", target = "menuTo"),
            @Mapping(source = "menu.icon", target = "menuIcon")
    })
    @Override
    RoleMenuDto toDto(RoleMenu roleMenu);

    @InheritInverseConfiguration
    @Override
    RoleMenu toModel(RoleMenuDto roleMenuDto);
}
