package dz.gouv.ppas.web.cagapps.business.data.entities.admin;

import dz.gouv.ppas.web.cagapps.business.data.dto.enums.RoleEnum;
import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user", schema = StaticData.DataBaseSchema.ADMIN_SCHEMA)
public class User implements UserDetails, Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String accountName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "department")
    private String department;

    @Column(name = "phone")
    private String phone;

    @Column(name = "expired")
    private boolean accountExpires;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "credentials")
    private boolean credentials;

    @Column(name = "new_account")
    private boolean nouveauCompte;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", schema = StaticData.DataBaseSchema.ADMIN_SCHEMA, joinColumns = {
            @JoinColumn(name = "user_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", nullable = false)})
    private List<Roles> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getLabel())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.accountName;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !this.accountExpires;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isSecretaire() {
        return !roles.isEmpty() && roles.stream()
                .filter(customer -> RoleEnum.Secretaire.toString().equals(customer.getLabel()))
                .findAny().isPresent();
    }
}
