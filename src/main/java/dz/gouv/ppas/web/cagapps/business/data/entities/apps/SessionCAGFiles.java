package dz.gouv.ppas.web.cagapps.business.data.entities.apps;

import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "session_cag_files", schema = StaticData.DataBaseSchema.APPS_SCHEMA)
public class SessionCAGFiles implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "label", unique = true)
    private String label;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "session_cag_id", referencedColumnName = "id")
    private SessionCAG sessionCAG;

    @Column(name = "file_path")
    private String filePath;

    @OneToMany(mappedBy = "sessionCAGFiles")
    private List<SessionFilesNotes> sessionFilesNotes;

}
