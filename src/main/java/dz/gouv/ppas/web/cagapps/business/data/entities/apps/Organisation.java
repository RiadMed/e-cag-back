package dz.gouv.ppas.web.cagapps.business.data.entities.apps;

import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "organisation", schema = StaticData.DataBaseSchema.APPS_SCHEMA)
public class Organisation implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 12)
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

    @Column(name = "phone")
    private String phone;

    @Column(name = "admin_mail")
    private String adminMail;

    @Column(name = "secretaire_mail")
    private String secretaireMail;

    @Column(name = "responsable_mail")
    private String responsableMail;

    @Column(name = "activer")
    private Boolean activer;

    @Column(name = "logo")
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "organisation_type_id", referencedColumnName = "id")
    private OrganisationType organisationType;


}
