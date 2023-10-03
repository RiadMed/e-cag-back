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
public class MenuDto implements Serializable {

    private Integer id;
    private Integer key;
    private String label;
    private String to;
    private String description;
    private String icon;
    private Integer ordre;
    private boolean disabled;
}
