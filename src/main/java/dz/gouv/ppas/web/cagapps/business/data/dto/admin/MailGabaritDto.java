package dz.gouv.ppas.web.cagapps.business.data.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailGabaritDto implements Serializable {

    private Integer id;
    private String code;
    private String gabarit;
    private String sujet;
    private boolean activer;
}
