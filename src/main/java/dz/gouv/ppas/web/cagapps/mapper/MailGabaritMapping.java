package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.MailGabaritDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.MailGabarit;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MailGabaritMapping extends GenericMapper<MailGabarit, MailGabaritDto> {
    MailGabaritMapping INSTANCE = Mappers.getMapper(MailGabaritMapping.class);

    @Override
    MailGabaritDto toDto(MailGabarit mailGabarit);

    @Override
    MailGabarit toModel(MailGabaritDto mailGabaritDto);
}
