package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.Organisation;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {OrganisationTypeMapping.class})
public interface OrganisationMapping extends GenericMapper<Organisation, OrganisationDto> {

    OrganisationMapping INSTANCE = Mappers.getMapper(OrganisationMapping.class);

    @Mappings({
            @Mapping(source = "id", target = "key"),
            @Mapping(source = "organisationType.id", target = "organisationTypeId"),
            @Mapping(source = "organisationType.label", target = "organisationTypeLabel")
    })
    @Override
    OrganisationDto toDto(Organisation organisation);

    @InheritInverseConfiguration
    @Override
    Organisation toModel(OrganisationDto organisationDto);

    @AfterMapping
    default Organisation doAfterMapping(@MappingTarget Organisation entity, OrganisationDto target) {
        if (target.getOrganisationTypeId() == null) {
            entity.setOrganisationType(null);
        }
        return entity;
    }
}
