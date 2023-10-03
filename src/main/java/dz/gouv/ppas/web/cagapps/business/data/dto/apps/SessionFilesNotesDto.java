package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Builder
@Data
public class SessionFilesNotesDto implements Serializable {

    private Long sessionCAGFilesId;
    private String username;
    private String note;

}
