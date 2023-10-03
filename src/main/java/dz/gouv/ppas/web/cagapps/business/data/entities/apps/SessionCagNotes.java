package dz.gouv.ppas.web.cagapps.business.data.entities.apps;

import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "session_cag_notes", schema = StaticData.DataBaseSchema.APPS_SCHEMA)
public class SessionCagNotes implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "usercode")
    private String userCode;

    @ManyToOne
    @JoinColumn(name = "session_cag_files_id", referencedColumnName = "id")
    private SessionCAGFiles sessionCAGFiles;

    @Column(name = "notes")
    private String notes;

    @Column(name = "notes_date")
    private LocalDateTime notesDate;
}
