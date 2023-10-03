package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationTypeDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.OrganisationType;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganisationTypeMapping  extends GenericMapper<OrganisationType, OrganisationTypeDto> {

    OrganisationTypeMapping INSTANCE = Mappers.getMapper(OrganisationTypeMapping.class);

    @Override
    OrganisationTypeDto toDto(OrganisationType organisationType);

    @Override
    OrganisationType toModel(OrganisationTypeDto organisationTypeDto);
}
