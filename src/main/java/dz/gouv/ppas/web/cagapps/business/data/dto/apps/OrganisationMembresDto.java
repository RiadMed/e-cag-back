package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class OrganisationMembresDto implements Serializable {

    private Integer organisationId;
    private String membreUserCode;
    private LocalDateTime membreDate;
    private Boolean responsable;
    private Boolean activer;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String organisationLabel;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String organisationAdresse;

    public OrganisationMembresDto(Integer organisationId, String membreUserCode, LocalDateTime membreDate, Boolean responsable, Boolean activer) {
        this.organisationId = organisationId;
        this.membreUserCode = membreUserCode;
        this.membreDate = membreDate;
        this.responsable = responsable;
        this.activer = activer;
    }
}
