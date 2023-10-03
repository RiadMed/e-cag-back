package dz.gouv.ppas.web.cagapps.business.data.entities.admin;

import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "access_token", schema = StaticData.DataBaseSchema.ADMIN_SCHEMA)
public class AccessToken implements Serializable {

    @Id
    @Column(name = "id_access")
    private UUID idAccess;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
