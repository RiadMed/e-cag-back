package dz.gouv.ppas.web.cagapps.business.data.entities.apps;

import dz.gouv.ppas.web.cagapps.business.data.entities.admin.Roles;
import dz.gouv.ppas.web.cagapps.business.data.entities.admin.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembresInviter implements Serializable {
    private Long key;
    private String fullName;
    private String email;
    private List<Roles> roles;

    public MembresInviter(User user) {
        this.key = user.getId();
        this.fullName = user.getLastName() + ' ' + user.getFirstName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}
