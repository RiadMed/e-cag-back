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
@Table(name = "session_cag_invitation", schema = StaticData.DataBaseSchema.APPS_SCHEMA)
public class SessionCAGInvitation implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "membre")
    private String membre;

    @NotNull
    @Size(max = 100)
    @Column(name = "mail")
    private String mail;

    @Column(name = "date_invitation")
    private LocalDateTime dateInvitation;

    @Column(name = "present")
    private boolean present;

    @Column(name = "absence_justifiee")
    private boolean absenceJustifiee;

    @ManyToOne
    @JoinColumn(name = "session_cag_id", referencedColumnName = "id")
    private SessionCAG sessionCAG;

    @Column(name = "invitation_status")
    private Boolean invitationStatus;

    @Column(name = "invitation_status_date")
    private LocalDateTime invitationStatusDate;
}
