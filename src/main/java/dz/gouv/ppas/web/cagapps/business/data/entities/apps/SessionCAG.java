package dz.gouv.ppas.web.cagapps.business.data.entities.apps;

import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "session_cag", schema = StaticData.DataBaseSchema.APPS_SCHEMA)
public class SessionCAG implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 25)
    @Column(name = "code", unique = true)
    private String code;

    @NotNull
    @Size(max = 100)
    @Column(name = "label", unique = true)
    private String label;

    @Column(name = "description")
    private String description;

    @Column(name = "adresse")
    private String adresse;

    @NotNull
    @Column(name = "annee")
    private Integer annee;

    @NotNull
    @Column(name = "mois")
    private Integer mois;

    @NotNull
    @Column(name = "type_session")
    private Integer typeSession;

    @Column(name = "session_date")
    private LocalDateTime sessionDate;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @Column(name = "pv_date")
    private LocalDateTime pvDate;

    @Column(name = "reporter_date")
    private LocalDateTime reporterDate;

    @Column(name = "reporter_cause")
    private String reporterCause;

    @Column(name = "valider_date")
    private LocalDateTime validerDate;

    @Column(name = "reserve")
    private boolean reserve;

    @Column(name = "valider_avec_reserve_date")
    private LocalDateTime validerAvecReserveDate;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "changement_status_date")
    private LocalDateTime changementStatusDate;

    @Column(name = "creer_par")
    private String creerPar;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "pv_ajouter_par")
    private String pvAjouterPar;

    @ManyToOne
    @JoinColumn(name = "organisation_id", referencedColumnName = "id")
    private Organisation organisation;


    @OneToMany(mappedBy = "sessionCAG")
    @OrderBy("id ASC")
    private List<SessionCAGFiles> files = new ArrayList<>();

    @OneToMany(mappedBy = "sessionCAG")
    @OrderBy("id ASC")
    private List<SessionCAGInvitation> invitations = new ArrayList<>();
}
