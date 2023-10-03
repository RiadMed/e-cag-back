package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SessionCAGInvitationDto implements Serializable {

    private Long id;
    private String membre;
    private String mail;
    private Long sessionCAGId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String sessionCAGCode;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String sessionCAGAdresse;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer sessionCAGTypeSession;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String sessionCAGOrganisationLabel;
    private LocalDateTime invitationStatusDate;
    private String invitationStatusDateTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String dateInvitationTime;

    private Boolean invitationStatus;
    private LocalDateTime dateInvitation;
    private boolean present;
    private boolean absenceJustifiee;
}
