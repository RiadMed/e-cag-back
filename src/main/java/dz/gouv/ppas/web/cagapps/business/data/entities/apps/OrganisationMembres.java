package dz.gouv.ppas.web.cagapps.business.data.entities.apps;

import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "organisation_membres", schema = StaticData.DataBaseSchema.APPS_SCHEMA)
public class OrganisationMembres implements Serializable {

    @EmbeddedId
    private OrganisationMembresPK id;

    @Column(name = "membre_date")
    private LocalDateTime membreDate;

    @Column(name = "activer")
    private Boolean activer;

    @Column(name = "responsable")
    private Boolean responsable;
}
