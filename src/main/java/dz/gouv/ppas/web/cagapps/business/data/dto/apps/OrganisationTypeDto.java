package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class OrganisationTypeDto implements Serializable {
    private Integer id;
    private String label;
    private Boolean activer;
}
