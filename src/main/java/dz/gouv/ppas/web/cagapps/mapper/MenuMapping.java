package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.MenuDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.Menu;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MenuMapping extends GenericMapper<Menu, MenuDto> {

    MenuMapping INSTANCE = Mappers.getMapper(MenuMapping.class);

    @Mappings({
            @Mapping(source = "id", target = "key")
    })
    @Override
    MenuDto toDto(Menu menu);

    @InheritInverseConfiguration
    @Override
    Menu toModel(MenuDto menuDto);
}
