package dz.gouv.ppas.web.cagapps.business.services.impl;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.RoleMenuDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.RoleMenu;
import dz.gouv.ppas.web.cagapps.business.repositories.RoleMenuDao;
import dz.gouv.ppas.web.cagapps.business.services.RoleMenuServices;
import dz.restmapping.apps.services.impl.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class RoleMenuServicesIMPL extends GenericServiceImpl<RoleMenuDao, RoleMenu, RoleMenuDto, Integer> implements RoleMenuServices {

    private final RoleMenuDao roleMenuDao;

    @Override
    public List<RoleMenuDto> getMenuByRole(List<Integer> roleIds) {
        return mapToDto(roleMenuDao.getMenuByRole(roleIds));
    }
}
