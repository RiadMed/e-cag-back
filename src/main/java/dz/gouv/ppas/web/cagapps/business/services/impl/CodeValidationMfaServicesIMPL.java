package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.CodeValidationMfaDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.CodeValidationMfa;
import dz.gouv.ppas.web.cagapps.business.repositories.CodeValidationMfaDao;
import dz.gouv.ppas.web.cagapps.business.services.CodeValidationMfaServices;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CodeValidationMfaServicesIMPL extends GenericServiceImpl<CodeValidationMfaDao, CodeValidationMfa, CodeValidationMfaDto, Long> implements CodeValidationMfaServices {
}
