package dz.gouv.ppas.web.cagapps.business.data.entities.apps;

import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "status", schema = StaticData.DataBaseSchema.APPS_SCHEMA)
public class Status implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(name = "label", unique = true)
    private String label;

    @Column(name = "description")
    private String description;

    @Column(name = "color")
    private String color;

    @Column(name = "disabled")
    private Boolean disabled;

}
