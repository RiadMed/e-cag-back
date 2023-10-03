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
@Table(name = "menu", schema = StaticData.DataBaseSchema.ADMIN_SCHEMA)
public class Menu implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "label", nullable = false)
    private String label;

    @NotNull
    @Column(name = "to", nullable = false)
    private String to;

    @Column(name = "description")
    private String description;

    @Column(name = "ordre")
    private Integer ordre;

    @Column(name = "icon")
    private String icon;

    @Column(name = "disabled")
    private boolean disabled;

}
