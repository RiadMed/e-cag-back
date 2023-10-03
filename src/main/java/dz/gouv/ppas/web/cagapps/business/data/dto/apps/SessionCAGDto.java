package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SessionCAGDto implements Serializable {

    private Long id;
    private String code;
    private String label;
    private String adresse;
    private String description;
    private Integer annee;
    private Integer mois;
    private Integer typeSession;
    private String sessionDateTime;
    private LocalDateTime sessionDate;
    private Integer organisationId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String organisationLabel;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String organisationAdresse;
    private Integer statusId;
    private String statusLabel;
    private LocalDateTime changementStatusDate;
    private LocalDateTime pvDate;
    private String filePath;
    private String pvAjouterPar;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String pvDateTime;
    private LocalDateTime reporterDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String reporterDateTime;
    private String reporterCause;
    private LocalDateTime validerDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String validerDateTime;
    private boolean reserve;
    private LocalDateTime validerAvecReserveDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String validerAvecReserveDateTime;
    private LocalDateTime creationDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String creationDateTime;
    private String creerPar;
    private List<SessionCAGInvitationDto> invitationsList = new ArrayList<>();
}
