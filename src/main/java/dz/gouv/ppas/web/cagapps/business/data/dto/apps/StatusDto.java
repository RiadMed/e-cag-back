package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class StatusDto implements Serializable {
    private Integer id;
    private String label;
    private String description;
    private String color;
    private Boolean disabled;
}
