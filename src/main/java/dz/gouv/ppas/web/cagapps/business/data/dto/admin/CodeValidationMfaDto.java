package dz.gouv.ppas.web.cagapps.business.data.dto.admin;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CodeValidationMfaDto implements Serializable {

    private Long id;
    private String usercode;
    private String codeValidation;
    private LocalDateTime date;
    private Integer numeroSeq;
    private Integer nbTentative;
    private Integer status;

    public CodeValidationMfaDto(Integer nbTentative) {
        this.nbTentative = nbTentative;
    }
}
