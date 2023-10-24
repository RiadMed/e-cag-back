package dz.gouv.ppas.web.cagapps.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecaptchaResponse implements Serializable {
    private Boolean success;
    private String challege_ts;
    private String hostname;
    private Double score;
    private String action;
}
