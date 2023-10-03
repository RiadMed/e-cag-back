package dz.gouv.ppas.web.cagapps.business.data.entities.admin;

import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "role", schema = StaticData.DataBaseSchema.ADMIN_SCHEMA)
public class Roles implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "label", unique = true)
    private String label;

    @OneToMany(mappedBy = "menu")
    private List<RoleMenu> menus;
}
