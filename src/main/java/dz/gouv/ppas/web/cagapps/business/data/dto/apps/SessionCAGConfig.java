package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionCAGConfig implements Serializable {

    private Long id;
    private String code;
    private String label;
    private String adresse;
    private String sessionDateTime;
    private Integer organisationId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String organisationLabel;
    private Integer statusId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String statusLabel;

    public SessionCAGConfig(SessionCAGDto sessionCAGDto) {
        this.id = sessionCAGDto.getId();
        this.code = sessionCAGDto.getCode();
        this.label = sessionCAGDto.getLabel();
        this.adresse = sessionCAGDto.getAdresse();
        this.sessionDateTime = sessionCAGDto.getSessionDateTime();
        this.organisationId = sessionCAGDto.getOrganisationId();
        this.organisationLabel = sessionCAGDto.getOrganisationLabel();
        this.statusId = sessionCAGDto.getStatusId();
        this.statusLabel = sessionCAGDto.getStatusLabel();
    }
}
