package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SessionStatusDto implements Serializable {

    private Long id;
    private LocalDateTime date;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String statusDateTime;
    private Long sessionId;
    private Integer statusId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String statusLabel;
    private Long userId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userFirstName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userLastName;

    public SessionStatusDto(Integer statusId, Long sessionId, Long userId) {
        this.date = LocalDateTime.now();
        this.sessionId = sessionId;
        this.statusId = statusId;
        this.userId = userId;
    }
}
