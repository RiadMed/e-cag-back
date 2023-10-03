package dz.gouv.ppas.web.cagapps.business.data.entities.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsSql implements Serializable {

    private Long id;
    private Long key;
    private String label;
}
