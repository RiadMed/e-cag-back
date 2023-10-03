package dz.gouv.ppas.web.cagapps.business.data.dto.apps;

import dz.gouv.ppas.web.cagapps.business.data.entities.apps.MembresInviter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MembresInviterDto implements Serializable {
    private Long key;
    private String fullName;
    private String email;

    public MembresInviterDto(MembresInviter membresInviter) {
        this.key = membresInviter.getKey();
        this.fullName = membresInviter.getFullName();
        this.email = membresInviter.getEmail();
    }
}
