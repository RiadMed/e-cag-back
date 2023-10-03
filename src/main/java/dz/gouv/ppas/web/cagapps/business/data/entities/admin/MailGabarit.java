package dz.gouv.ppas.web.cagapps.business.data.entities.admin;

import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "mail_gabarit", schema = StaticData.DataBaseSchema.ADMIN_SCHEMA)
public class MailGabarit implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "gabarit", nullable = false)
    private String gabarit;

    @Column(name = "sujet", nullable = false)
    private String sujet;

    @Column(name = "activer")
    private boolean activer;
}
