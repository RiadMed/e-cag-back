package dz.gouv.ppas.web.cagapps.business.data.entities.admin;

import dz.gouv.ppas.web.cagapps.tools.StaticData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "code_validation_mfa", schema = StaticData.DataBaseSchema.ADMIN_SCHEMA)
public class CodeValidationMfa implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "usercode")
    private String usercode;

    @NotNull
    @Size(max = 6)
    @Column(name = "code_validation", unique = true)
    private String code_validation;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @NotNull
    @Column(name = "numero_seq")
    private Integer numeroSeq;

    @NotNull
    @Column(name = "nb_tentative")
    private Integer nbTentative;

    @NotNull
    @Column(name = "status")
    private Integer status;

}
