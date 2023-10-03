package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionResolutionsDto implements Serializable {

    private Long id;
    private String description;
    private LocalDateTime dateCreation;
    private boolean status;
    private LocalDateTime changeDate;
    private Long sessionCAGId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String sessionCAGCode;
}
