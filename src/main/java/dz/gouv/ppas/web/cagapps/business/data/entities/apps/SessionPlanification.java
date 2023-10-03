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
@Table(name = "session_planification", schema = StaticData.DataBaseSchema.APPS_SCHEMA)
public class SessionPlanification implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organisation_id", referencedColumnName = "id")
    private Organisation organisation;

    @Column(name = "planification_date")
    private LocalDateTime planificationDate;

    @Column(name = "type")
    private Integer type;

    @Column(name = "valider_par")
    private String validerPar;

    @Column(name = "validation_date")
    private LocalDateTime validationDate;

    @Column(name = "creer_par")
    private String creerPar;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
