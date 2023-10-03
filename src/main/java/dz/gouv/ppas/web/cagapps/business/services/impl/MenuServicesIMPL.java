package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.MenuDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.Menu;
import dz.gouv.ppas.web.cagapps.business.repositories.MenuDao;
import dz.gouv.ppas.web.cagapps.business.services.MenuServices;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuServicesIMPL extends GenericServiceImpl<MenuDao, Menu, MenuDto, Integer> implements MenuServices {
}
