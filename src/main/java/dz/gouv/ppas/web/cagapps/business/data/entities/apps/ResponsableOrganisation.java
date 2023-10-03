package dz.gouv.ppas.web.cagapps.business.data.entities.apps;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsableOrganisation implements Serializable {

    private Integer organisationId;
    private String organisationLabel;
    private String organisationAdresse;
    private boolean responsable;

    public ResponsableOrganisation(OrganisationMembres organisationMembres, Organisation organisation) {
        this.organisationId = organisationMembres.getId().getOrganisation().getId();
        this.organisationLabel = organisation.getCode();
        this.organisationAdresse = organisation.getAdresse();
        this.responsable = organisationMembres.getResponsable() != null ? organisationMembres.getResponsable() : false;
    }
}
