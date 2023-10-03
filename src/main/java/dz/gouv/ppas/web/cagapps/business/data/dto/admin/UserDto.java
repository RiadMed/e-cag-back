package dz.gouv.ppas.web.cagapps.business.data.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationDetailDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.OrganisationMembresDto;
import dz.gouv.ppas.web.cagapps.business.data.dto.apps.ResponsableOrganisationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long key;

    private String accountName;
    private String firstName;
    private String lastName;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String department;
    private String phone;
    private boolean nouveauCompte;
    @JsonIgnore
    private boolean accountExpires;
    private boolean enabled;
    private boolean credentials;
    private boolean secretaire;
    private String organisationResponsable;
    private Integer organisationResponsableId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String organisationResponsableAdresse;

    private List<OrganisationDetailDto> organisationList;
    private List<RolesDto> rolesList;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<OrganisationDto> organisationIds;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isAdmin;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isMembre;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isSecretaire;

    public String getFullName() {
        return firstName + " " + lastName;
    }

}
