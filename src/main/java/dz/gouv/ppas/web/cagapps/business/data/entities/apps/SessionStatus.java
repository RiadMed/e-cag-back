package dz.gouv.ppas.web.cagapps.business.data.entities.apps;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.User;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "session_status", schema = StaticData.DataBaseSchema.APPS_SCHEMA)
public class SessionStatus implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_cag_id", referencedColumnName = "id")
    private SessionCAG sessionCAG;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "date")
    private LocalDateTime date;
}
