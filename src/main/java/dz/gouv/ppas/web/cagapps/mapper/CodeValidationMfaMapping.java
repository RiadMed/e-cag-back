package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.CodeValidationMfaDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.CodeValidationMfa;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CodeValidationMfaMapping extends GenericMapper<CodeValidationMfa, CodeValidationMfaDto> {

    CodeValidationMfaMapping INSTANCE = Mappers.getMapper(CodeValidationMfaMapping.class);

    @Override
    CodeValidationMfaDto toDto(CodeValidationMfa codeValidationMfa);

    @Override
    CodeValidationMfa toModel(CodeValidationMfaDto codeValidationMfaDto);
}
