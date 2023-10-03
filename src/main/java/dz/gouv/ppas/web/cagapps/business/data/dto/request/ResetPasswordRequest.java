package dz.gouv.ppas.web.cagapps.business.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest implements Serializable {

    private Long userId;
    private String newPassword;
    private String confirmPassword;

}
