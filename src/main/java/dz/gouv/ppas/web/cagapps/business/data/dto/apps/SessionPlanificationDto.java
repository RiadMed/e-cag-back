package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SessionPlanificationDto implements Serializable {

    private Long id;
    private Integer organisationId;
    private String organisationLabel;
    private LocalDateTime planificationDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String planificationDateTime;

    private Integer type;
    private String validerPar;
    private LocalDateTime validationDate;
    private String creerPar;
    private LocalDateTime creationDate;
}
