package dz.gouv.ppas.web.cagapps.business.data.entities.apps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class SessionFilesNotesPK implements Serializable {

    @NotNull
    @Column(name = "session_cag_files_id")
    private Long sessionCAGFilesId;

    @NotNull
    @Column(name = "username")
    private String username;
}
