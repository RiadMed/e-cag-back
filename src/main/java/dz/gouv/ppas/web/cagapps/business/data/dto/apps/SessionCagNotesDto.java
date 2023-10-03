package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SessionCagNotesDto implements Serializable {

    private Long id;
    private String userCode;
    private Long sessionCAGFilesId;
    private String notes;
    private LocalDateTime notesDate;
}
