package dz.gouv.ppas.web.cagapps.business.data.entities.wrapper;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGFiles;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SessionFileNoteWrapper implements Serializable {
    private Long id;
    private String label;
    private String description;
    private String note;

    public SessionFileNoteWrapper(SessionCAGFiles sessionCAGFiles, String note) {
        this.id = sessionCAGFiles.getId();
        this.label = sessionCAGFiles.getLabel();
        this.description = sessionCAGFiles.getDescription();
        this.note = note;
    }
}
