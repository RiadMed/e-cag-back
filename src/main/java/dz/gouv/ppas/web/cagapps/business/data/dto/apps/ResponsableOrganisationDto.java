package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.ResponsableOrganisation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsableOrganisationDto implements Serializable {

    private Integer key;
    private Integer organisationId;
    private String organisationLabel;
    private String organisationAdresse;
    private boolean responsable;

    public ResponsableOrganisationDto(ResponsableOrganisation responsableOrganisation) {
        this.key = responsableOrganisation.getOrganisationId();
        this.organisationId = responsableOrganisation.getOrganisationId();
        this.organisationLabel = responsableOrganisation.getOrganisationLabel();
        this.organisationAdresse = responsableOrganisation.getOrganisationAdresse();
        this.responsable = responsableOrganisation.isResponsable();
    }
}
