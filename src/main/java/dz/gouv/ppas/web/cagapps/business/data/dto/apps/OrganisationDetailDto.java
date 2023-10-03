package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganisationDetailDto implements Serializable {
    private Integer id;
    private Integer key;
    private String label;
}
