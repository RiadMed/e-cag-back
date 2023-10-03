package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class OrganisationDto implements Serializable {

    private Integer id;
    private Integer key;
    private String code;
    private String label;
    private String description;
    private String adresse;
    private String phone;
    private String adminMail;
    private String secretaireMail;
    private String responsableMail;
    private byte[] logo;
    private Boolean activer;
    private Integer organisationTypeId;
    private String organisationTypeLabel;

}
