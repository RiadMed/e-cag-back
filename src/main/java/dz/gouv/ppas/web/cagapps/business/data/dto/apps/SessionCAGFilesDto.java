package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class SessionCAGFilesDto implements Serializable {
    private Long id;
    private String label;
    private String description;
    @Transient
    private String file;
    private String filePath;
    private Long sessionCAGId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String sessionCAGCode;
}
