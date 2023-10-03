package dz.gouv.ppas.web.cagapps.web;

import dz.gouv.ppas.web.cagapps.business.data.dto.admin.MenuDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.admin.RoleMenuDto;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.Menu;
import dz.gouv.ppas.web.cagapps.business.services.MenuServices;
import dz.gouv.ppas.web.cagapps.business.services.RoleMenuServices;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import dz.restmapping.apps.web.GenericRestController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(StaticData.ApiMapping.MENU_ROOT)
public class MenuController extends GenericRestController<MenuServices, Menu, MenuDto, Integer> {

    private final RoleMenuServices roleMenuServices;


    @GetMapping(
            params = {"rolesIds"}
    )
    public List<RoleMenuDto> getMenuByRole(@RequestParam("rolesIds") List<Integer> roleIds) {
        HashSet<String> seen = new HashSet<>();
        List<RoleMenuDto> list = roleMenuServices.getMenuByRole(roleIds);
        list.removeIf(e -> !seen.add(e.getMenuLabel()));
        return list;
    }
}
