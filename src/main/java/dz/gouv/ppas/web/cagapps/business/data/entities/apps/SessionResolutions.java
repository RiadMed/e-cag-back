package dz.gouv.ppas.web.cagapps.business.data.entities.apps;

import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "session_resolutions", schema = StaticData.DataBaseSchema.APPS_SCHEMA)
public class SessionResolutions implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "status")
    private boolean status;

    @Column(name = "change_date")
    private LocalDateTime changeDate;

    @ManyToOne
    @JoinColumn(name = "session_cag_id", referencedColumnName = "id")
    private SessionCAG sessionCAG;

}
