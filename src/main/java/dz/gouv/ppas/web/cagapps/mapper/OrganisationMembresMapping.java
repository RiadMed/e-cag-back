package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationMembresDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.OrganisationMembres;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {OrganisationMapping.class})
public interface OrganisationMembresMapping extends GenericMapper<OrganisationMembres, OrganisationMembresDto> {

    OrganisationMembresMapping INSTANCE = Mappers.getMapper(OrganisationMembresMapping.class);

    @Mappings({
            @Mapping(source = "id.organisation.id", target = "organisationId"),
            @Mapping(source = "id.organisation.label", target = "organisationLabel"),
            @Mapping(source = "id.membreUserCode", target = "membreUserCode")
    })
    @Override
    OrganisationMembresDto toDto(OrganisationMembres organisationMembres);

    @InheritInverseConfiguration
    @Override
    OrganisationMembres toModel(OrganisationMembresDto organisationMembresDto);

    @AfterMapping
    default OrganisationMembres doAfterMapping(@MappingTarget OrganisationMembres entity, OrganisationMembresDto target) {
        if (target.getOrganisationId() == null && target.getMembreUserCode() == null) {
            entity.setId(null);
        }
        return entity;
    }
}
