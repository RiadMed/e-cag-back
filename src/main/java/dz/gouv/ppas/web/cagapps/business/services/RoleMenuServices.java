package dz.gouv.ppas.web.cagapps.business.services;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.RoleMenuDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.RoleMenu;
import dz.restmapping.apps.services.GenericService;

import java.util.List;

public interface RoleMenuServices extends GenericService<RoleMenu, RoleMenuDto, Integer> {

     List<RoleMenuDto> getMenuByRole(List<Integer> roleIds);
}
