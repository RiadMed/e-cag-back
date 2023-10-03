package dz.gouv.ppas.web.cagapps.business.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NextStatusRequest implements Serializable {
    private Long sessionId;
    private String statusLabel;
}
