package dz.gouv.ppas.web.cagapps.mapper;

import dz.gouv.ppas.web.cagapps.business.data.dto.apps.SessionPlanificationDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionPlanification;
import dz.restmapping.apps.mapper.config.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SessionPlanificationMapping extends GenericMapper<SessionPlanification, SessionPlanificationDto> {
    SessionPlanificationMapping INSTANCE = Mappers.getMapper(SessionPlanificationMapping.class);
}
