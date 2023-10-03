package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.SessionCAGFiles;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SessionFileView implements Serializable {
    private Long id;
    private String label;
    private String description;
    private String note;
    private String filePath;

    public SessionFileView(SessionCAGFiles sessionCAGFiles, String note) {
        this.id = sessionCAGFiles.getId();
        this.label = sessionCAGFiles.getLabel();
        this.description = sessionCAGFiles.getDescription();
        this.filePath = sessionCAGFiles.getFilePath();
        this.note = note;
    }

}
