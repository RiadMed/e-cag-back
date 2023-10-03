package dz.gouv.ppas.web.cagapps.business.data.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuDto implements Serializable {
    private Integer id;
    private Integer key;
    private Integer roleId;
    private String roleLabel;
    private Integer menuId;
    private String menuLabel;
    private String menuDescription;
    private String menuIcon;
    private String menuTo;
    private boolean create;
    private boolean update;
    private boolean delete;
    private boolean search;
    private boolean print;
}
