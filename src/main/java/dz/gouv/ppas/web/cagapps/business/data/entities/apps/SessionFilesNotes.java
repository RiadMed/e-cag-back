package dz.gouv.ppas.web.cagapps.business.data.entities.apps;

import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "session_files_notes", schema = StaticData.DataBaseSchema.APPS_SCHEMA)
public class SessionFilesNotes implements Serializable {

    @EmbeddedId
    private SessionFilesNotesPK id;

    @NotNull
    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "session_cag_files_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SessionCAGFiles sessionCAGFiles;
}
