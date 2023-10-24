package dz.gouv.ppas.web.cagapps.business.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MfaRequest implements Serializable {
    private String userCode;
    private String codeValidation;
}
