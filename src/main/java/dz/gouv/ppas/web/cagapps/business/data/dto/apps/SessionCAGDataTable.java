package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionCAGDataTable implements Serializable {
    private Long id;
    private String code;
    private String label;
    private String adresse;
    private Integer typeSession;
    private Integer organisationId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String organisationLabel;
    private Integer statusId;
    private String statusLabel;
    private String sessionDateTime;
}
