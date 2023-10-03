package dz.gouv.ppas.web.cagapps.business.data.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolesDto implements Serializable {
    private Long id;
    private Integer key;
    private String label;

    private List<RoleMenuDto> menusList;
}
